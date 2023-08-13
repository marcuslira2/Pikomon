package br.com.pikomon.Pikomon.schedule;

import br.com.pikomon.Pikomon.modal.MoveData;
import br.com.pikomon.Pikomon.modal.PokemonData;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.PokemonMove;
import br.com.pikomon.Pikomon.repository.MoveRepository;
import br.com.pikomon.Pikomon.repository.PokemonMoveRepository;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
            for (int i=1;i<151;i++){
                Pokemon pk = new Pokemon();
                PokemonData pokemonData = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/"+i,PokemonData.class);
                assert pokemonData != null;
                String pokeSaved = "Saving pokemon: " + pokemonData.getName();

                log.info(pokeSaved);

                //Ajustar meu objeto de persistencia para que ele tenha uma lista com o nome e level que aprende o golpe.(isso vai ser outra classe)

                pk.setName(pokemonData.getName());
                pk.setDisplayName(pokemonData.getName());
                pk.setId(pokemonData.getId());
                pk.setBaseExp(pokemonData.getBase_experience());
                pk.setBaseHp(pokemonData.getStats().get(0).getBase_stat());
                pk.setBaseAtk(pokemonData.getStats().get(1).getBase_stat());
                pk.setBaseDef(pokemonData.getStats().get(2).getBase_stat());
                pk.setBaseSpAtk(pokemonData.getStats().get(3).getBase_stat());
                pk.setBaseSpDef(pokemonData.getStats().get(4).getBase_stat());
                pk.setBaseSpeed(pokemonData.getStats().get(5).getBase_stat());
                pk.setType1(pokemonData.getTypes().get(0).getType().getName());
                pk.setShiny(false);
                if (pokemonData.getTypes().size()>1){
                    pk.setType2(pokemonData.getTypes().get(1).getType().getName());
                }
                List<MoveData> moveData = pokemonData.getMoves();
                for (MoveData moveDatum : moveData){
                    if (moveDatum.getVersion_group_details().get(0).getLevel_learned_at() !=0){
                        PokemonMove pokemonMove = new PokemonMove();
                        pokemonMove.setMoveName(moveDatum.getMove().getName());
                        pokemonMove.setPokemonName(pokemonData.getName());
                        pokemonMove.setLevel(moveDatum.getVersion_group_details().get(0).getLevel_learned_at());
                        pkMoveRepository.save(pokemonMove);
                    }
                }
                pokemonRepository.save(pk);
            }
        }catch (Exception e){
            log.info(e.getMessage());
        }
    }



}
