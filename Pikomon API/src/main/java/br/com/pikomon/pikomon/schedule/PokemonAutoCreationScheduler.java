package br.com.pikomon.pikomon.schedule;

import br.com.pikomon.pikomon.enums.TypeEnum;
import br.com.pikomon.pikomon.modal.MoveData;
import br.com.pikomon.pikomon.modal.PokemonData;
import br.com.pikomon.pikomon.persistence.Pokemon;
import br.com.pikomon.pikomon.persistence.PokemonMove;
import br.com.pikomon.pikomon.persistence.Status;
import br.com.pikomon.pikomon.repository.PokemonMoveRepository;
import br.com.pikomon.pikomon.repository.PokemonRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonAutoCreationScheduler {

    private final RestTemplate restTemplate;

    private final PokemonRepository pokemonRepository;

    private final PokemonMoveRepository pkMoveRepository;

    private static final Logger log = LoggerFactory.getLogger(PokemonAutoCreationScheduler.class);

    public PokemonAutoCreationScheduler(RestTemplate restTemplate, PokemonRepository pokemonRepository, PokemonMoveRepository pkMoveRepository) {
        this.restTemplate = restTemplate;
        this.pokemonRepository = pokemonRepository;
        this.pkMoveRepository = pkMoveRepository;
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
                    pk.setType1(TypeEnum.valueOf(pokemonData.getTypes().get(0).getType().getName().toUpperCase()));
                    pk.setShiny(false);
                    pk.setDeleted(0);

                    if (pokemonData.getTypes().size()>1){
                        pk.setType2(TypeEnum.valueOf(pokemonData.getTypes().get(1).getType().getName().toUpperCase()));
                    }
                    Status hp = new Status();
                    hp.setName(pokemonData.getStats().get(0).getStat().getName());
                    hp.setBaseStatus(pokemonData.getStats().get(0).getBase_stat());
                    hp.setEffort(pokemonData.getStats().get(0).getEffort());
                    hp.setEv(0);
                    pk.setHp(hp);
                    Status attack =new Status();
                    attack.setName(pokemonData.getStats().get(1).getStat().getName());
                    attack.setBaseStatus(pokemonData.getStats().get(1).getBase_stat());
                    attack.setEffort(pokemonData.getStats().get(1).getEffort());
                    attack.setEv(0);
                    pk.setAttack(attack);
                    Status defense = new Status();
                    defense.setName(pokemonData.getStats().get(2).getStat().getName());
                    defense.setBaseStatus(pokemonData.getStats().get(2).getBase_stat());
                    defense.setEffort(pokemonData.getStats().get(2).getEffort());
                    defense.setEv(0);
                    pk.setDefense(defense);
                    Status spAttack = new Status();
                    spAttack.setName(pokemonData.getStats().get(3).getStat().getName());
                    spAttack.setBaseStatus(pokemonData.getStats().get(3).getBase_stat());
                    spAttack.setEffort(pokemonData.getStats().get(3).getEffort());
                    spAttack.setEv(0);
                    pk.setSpAttack(spAttack);
                    Status spDefense = new Status();
                    spDefense.setName(pokemonData.getStats().get(4).getStat().getName());
                    spDefense.setBaseStatus(pokemonData.getStats().get(4).getBase_stat());
                    spDefense.setEffort(pokemonData.getStats().get(4).getEffort());
                    spDefense.setEv(0);
                    pk.setSpDefense(spDefense);
                    Status speed = new Status();
                    speed.setName(pokemonData.getStats().get(5).getStat().getName());
                    speed.setBaseStatus(pokemonData.getStats().get(5).getBase_stat());
                    speed.setEffort(pokemonData.getStats().get(5).getEffort());
                    speed.setEv(0);
                    pk.setSpeed(speed);

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
