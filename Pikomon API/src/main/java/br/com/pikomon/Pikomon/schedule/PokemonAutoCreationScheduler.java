package br.com.pikomon.Pikomon.schedule;

import br.com.pikomon.Pikomon.modal.*;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.PokemonMove;
import br.com.pikomon.Pikomon.repository.MoveRepository;
import br.com.pikomon.Pikomon.repository.PokemonMoveRepository;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonAutoCreationScheduler {

    private final RestTemplate restTemplate;

    private final PokemonRepository pokemonRepository;

    private final PokemonMoveRepository pkMoveRepository;

    private final MoveRepository moveRepository;

    private static final Logger log = LoggerFactory.getLogger(PokemonAutoCreationScheduler.class);

    public PokemonAutoCreationScheduler(RestTemplate restTemplate, PokemonRepository pokemonRepository, PokemonMoveRepository pkMoveRepository, MoveRepository moveRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
        this.pkMoveRepository = pkMoveRepository;
        this.moveRepository = moveRepository;
    }

    @PostConstruct
    public void createPokemon(){

        try {
//            log.info("Baixando imagens");
//            for (int count = 1; count <= 151; count++) {
//                String url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/" + count + ".gif";
//                String path = "C:\\image\\"+count+".gif";
//                imageDownloader(url, path);
//                log.info("pokemon baixado sob id: "+count);
//                url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/versions/generation-v/black-white/animated/shiny/" + count + ".gif";
//                path = "C:\\image\\"+count+"-shiny.gif";
//                imageDownloader(url, path);
//                log.info("pokemon shiny baixado sob id: "+count);
//
//            }

            log.info("Creating pokemons on database");
            for (int i=1;i<152;i++){

                Optional<Pokemon> isPokemonExists = pokemonRepository.findById((long) i);
                if (isPokemonExists.isEmpty()){

                    Pokemon pk = new Pokemon();
                    PokemonData pokemonData = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/"+i,PokemonData.class);
                    assert pokemonData != null;
                    String pokeSaved = "Saving pokemon: " + pokemonData.getName();
                    log.info(pokeSaved);

                    pk.setName(pokemonData.getName());
                    pk.setDisplayName(pokemonData.getName());
                    pk.setNumber(pokemonData.getId());
                    pk.setBaseExp(pokemonData.getBase_experience());
                    pk.setType1(pokemonData.getTypes().get(0).getType().getName());
                    pk.setShiny(false);
                    pk.setDeleted(0);

                    if (pokemonData.getTypes().size()>1){
                        pk.setType2(pokemonData.getTypes().get(1).getType().getName());
                    }

                    for (int j =0; j< 6; j++){
                    pk.getBase().add(pokemonData.getStats().get(j).getBase_stat());
                    }
                    List<StatData> statData = pokemonData.getStats();
                    for (StatData statDatum : statData){
                        if (statDatum.getEffort()!=0){
                            pk.setEffortValue(statDatum.getEffort());
                            pk.setEffortType(statDatum.getStat().getName());
                        }
                    }

                    List<MoveData> moveData = pokemonData.getMoves();
                    List<PokemonMove> moveToAdd = new ArrayList<>();
                    for (MoveData moveDatum : moveData){
                        if (moveDatum.getVersion_group_details().get(0).getLevel_learned_at() !=0){
                            PokemonMove pokemonMove = new PokemonMove();
                            pokemonMove.setMoveName(moveDatum.getMove().getName());
                            pokemonMove.setPokemonName(pokemonData.getName());
                            pokemonMove.setLevel(moveDatum.getVersion_group_details().get(0).getLevel_learned_at());
                            pkMoveRepository.save(pokemonMove);
                            moveToAdd.add(pokemonMove);
                        }
                    }
                    pk.getMoves().addAll(moveToAdd);
                    pokemonRepository.save(pk);

                }

            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }

    public void imageDownloader(String urlOrigin,String savePath){
        try {
            URL url = new URL(urlOrigin);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (InputStream inputStream = httpURLConnection.getInputStream();
                     FileOutputStream outputStream = new FileOutputStream(savePath)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }
            } else {
                System.out.println("Falha ao baixar a imagem. Código de status: " + responseCode);
            }
        } catch (IOException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }



}
