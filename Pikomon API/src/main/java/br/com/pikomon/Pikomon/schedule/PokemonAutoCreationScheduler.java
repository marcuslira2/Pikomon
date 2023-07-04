package br.com.pikomon.Pikomon.schedule;

import br.com.pikomon.Pikomon.modal.DescriptionData;
import br.com.pikomon.Pikomon.modal.MoveData;
import br.com.pikomon.Pikomon.modal.MoveRequestData;
import br.com.pikomon.Pikomon.modal.PokemonData;
import br.com.pikomon.Pikomon.persistence.Move;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.repository.MoveRepository;
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
    private MoveRepository moveRepository;

    private static final Logger log = LoggerFactory.getLogger(PokemonAutoCreationScheduler.class);

    @PostConstruct
    public void create(){
        log.info("Creating pokemons on database");
        for (int i=1;i<3;i++){
            Pokemon pk = new Pokemon();
            PokemonData pokemonData = restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon/"+i,PokemonData.class);
            log.info("Saving pokemon: " + pokemonData.getName());

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

            for (int f=0;f<moveData.size();f++){
                String ur = moveData.get(f).getMove().getUrl();
                MoveRequestData movesResquest = restTemplate.getForObject(ur,MoveRequestData.class);

                if (moveData.get(f).getVersion_group_details().get(0).getLevel_learned_at()!=0){
                    Move move = new Move();
                    assert movesResquest != null;
                    move.setId(movesResquest.getId());
                    move.setName(movesResquest.getName());
                    move.setPower(movesResquest.getPower());
                    move.setPp(movesResquest.getPp());
                    move.setAccuracy(movesResquest.getAccuracy());
                    move.setDamage_class(movesResquest.getDamage_class().getName());
                    move.setPriority(movesResquest.getPriority());
                    move.setType(movesResquest.getType().getName());
                    move.setLearnLevel(moveData.get(f).getVersion_group_details().get(0).getLevel_learned_at());
                    List<DescriptionData> descriptionData = movesResquest.getEffect_entries();
                    if (!descriptionData.isEmpty()){
                        move.setDescription(descriptionData.get(0).getShort_effect());

                    }
                    log.info("Saving move: " + move.getName());
                    moveRepository.save(move);
                    pk.add(move);
                }
            }
            pokemonRepository.save(pk);
        }

    }



}
