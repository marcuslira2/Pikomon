package br.com.pikomon.pikomon.service.pokemon;

import br.com.pikomon.pikomon.enums.NatureEnum;
import br.com.pikomon.pikomon.persistence.Pokemon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class PokemonAttributesService {

    private final Random rnd = new Random();

    public Pokemon calcBattleStatus(Pokemon pokemon) {

        int hp = ((2*pokemon.getHp().getBaseStatus()+pokemon.getHp().getIv()+(pokemon.getHp().getEv()/4))*pokemon.getLevel()/100+10);
        int atk = ((2 * pokemon.getAttack().getBaseStatus()+pokemon.getAttack().getIv()+ (pokemon.getAttack().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int def = ((2 * pokemon.getDefense().getBaseStatus()+pokemon.getDefense().getIv() + (pokemon.getDefense().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int spAtk = ((2 * pokemon.getSpAttack().getBaseStatus()+pokemon.getSpAttack().getIv() + (pokemon.getSpAttack().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int spDef = ((2 * pokemon.getSpDefense().getBaseStatus()+pokemon.getSpDefense().getIv() + (pokemon.getSpDefense().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int speed = ((2 * pokemon.getSpeed().getBaseStatus()+pokemon.getSpeed().getIv() + (pokemon.getSpeed().getEv()/4)) * pokemon.getLevel()/100 + 5);

        pokemon.getHp().setBattleStatus(hp);
        pokemon.getAttack().setBattleStatus(atk);
        pokemon.getDefense().setBattleStatus(def);
        pokemon.getSpAttack().setBattleStatus(spAtk);
        pokemon.getSpDefense().setBattleStatus(spDef);
        pokemon.getSpeed().setBattleStatus(speed);

        return pokemon.getNature().calcNature(pokemon);

    }

    public NatureEnum generateNature() {
        ArrayList<NatureEnum> natureList = new ArrayList<>();
        Collections.addAll(natureList,
                NatureEnum.ADAMANT,NatureEnum.BOLD,NatureEnum.NAIVE,NatureEnum.BRAVE
                        ,NatureEnum.CAREFUL,NatureEnum.LAX,NatureEnum.LONELY,NatureEnum.IMPISH
                        ,NatureEnum.NAUGHTY,NatureEnum.MODEST,NatureEnum.RELAXED,NatureEnum.MILD
                        ,NatureEnum.RASH,NatureEnum.QUIET,NatureEnum.GENTLE,NatureEnum.CALM
                        ,NatureEnum.SASSY,NatureEnum.TIMID,NatureEnum.JOLLY,NatureEnum.HASTY
                        ,NatureEnum.HARDY,NatureEnum.DOCILE,NatureEnum.QUIRKY,NatureEnum.SERIOUS);

        int selectNature = this.rnd.nextInt(natureList.size());
        return natureList.get(selectNature);
    }

    public List<Integer> generateIv() {
        List<Integer> ivList = new ArrayList<>(6);

        ivList.add(0, rnd.nextInt(31));
        ivList.add(1, rnd.nextInt(31));
        ivList.add(2, rnd.nextInt(31));
        ivList.add(3, rnd.nextInt(31));
        ivList.add(4, rnd.nextInt(31));
        ivList.add(5, rnd.nextInt(31));
        return ivList;
    }
}
