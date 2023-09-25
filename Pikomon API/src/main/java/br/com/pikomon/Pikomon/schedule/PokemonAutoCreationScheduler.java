package br.com.pikomon.Pikomon.schedule;

import br.com.pikomon.Pikomon.modal.MoveData;
import br.com.pikomon.Pikomon.modal.PokemonData;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.PokemonMove;
import br.com.pikomon.Pikomon.repository.MoveRepository;
import br.com.pikomon.Pikomon.repository.PokemonMoveRepository;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import br.com.pikomon.Pikomon.service.PokemonService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PokemonAutoCreationScheduler {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonMoveRepository pkMoveRepository;

    @Autowired
    private MoveRepository moveRepository;

    private static final Logger log = LoggerFactory.getLogger(PokemonAutoCreationScheduler.class);

    @PostConstruct
    public void createPokemon(){

        try {
            log.info("Creating pokemons on database");
            for (int i=1;i<10;i++){

                Optional<Pokemon> isPokemonExists = pokemonRepository.findById((long) i);
                if (isPokemonExists.isEmpty()){

                    Pokemon pk = new Pokemon();
                    PokemonData pokemonData = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/"+i,PokemonData.class);
                    assert pokemonData != null;
                    String pokeSaved = "Saving pokemon: " + pokemonData.getName();

                    log.info(pokeSaved);

                    pk.setName(pokemonData.getName());
                    pk.setDisplayName(pokemonData.getName());
                    pk.setId(pokemonData.getId());
                    pk.setBaseExp(pokemonData.getBase_experience());
                    for (int j =0; j< 6; j++){
                    pk.getBase().add(pokemonData.getStats().get(j).getBase_stat());
                    }
                    pk.setType1(pokemonData.getTypes().get(0).getType().getName());
                    pk.setShiny(false);
                    pk.setDeleted(0);
                    if (pokemonData.getTypes().size()>1){
                        pk.setType2(pokemonData.getTypes().get(1).getType().getName());
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



}
