package br.com.pikomon.pikomon.service.pokemon;

import br.com.pikomon.pikomon.persistence.Pokemon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class PokemonAttributesService {

    private final Random rnd = new Random();

    private final String ADAMANT = "Adamant";
    private final String NAUGHTY = "Naughty";
    private final String LONELY = "Lonely";
    private final String BRAVE = "Brave";
    private final String BOLD = "Bold";
    private final String IMPISH = "Impish";
    private final String LAX = "Lax";
    private final String RELAXED = "Relaxed";
    private final String MODEST = "Modest";
    private final String MILD = "Mild";
    private final String RASH = "Rash";
    private final String QUIET = "Quiet";
    private final String CALM = "Calm";
    private final String GENTLE = "Gentle";
    private final String CAREFUL = "Careful";
    private final String SASSY = "Sassy";
    private final String TIMID = "Timid";
    private final String HASTY = "Hasty";
    private final String JOLLY = "Jolly";
    private final String NAIVE = "Naive";


    public Pokemon calcBattleStatus(Pokemon pokemon) {
        List<Integer> status = new ArrayList<>(6);

        int hp = (((2*pokemon.getHp().getBaseStatus()+pokemon.getHp().getIv()+(pokemon.getHp().getEV()/4))*pokemon.getLevel()/100+10));
        int atk = (((2 * pokemon.getAttack().getBaseStatus()+pokemon.getAttack().getIv()+ (pokemon.getAttack().getEV()/4)) * pokemon.getLevel()/100 + 5));
        int def = (((2 * pokemon.getDefense().getBaseStatus()+pokemon.getDefense().getIv() + (pokemon.getDefense().getEV()/4)) * pokemon.getLevel()/100 + 5));
        int spAtk = (((2 * pokemon.getSpAttack().getBaseStatus()+pokemon.getSpAttack().getIv() + (pokemon.getSpAttack().getEV()/4)) * pokemon.getLevel()/100 + 5));
        int spDef = (((2 * pokemon.getSpDefense().getBaseStatus()+pokemon.getSpDefense().getIv() + (pokemon.getSpDefense().getEV()/4)) * pokemon.getLevel()/100 + 5));
        int speed = (((2 * pokemon.getSpeed().getBaseStatus()+pokemon.getSpeed().getIv() + (pokemon.getSpeed().getEV()/4)) * pokemon.getLevel()/100 + 5));

        String nature = pokemon.getNature();

        //BUFF
        if (nature.equals(LONELY) || nature.equals(ADAMANT) || nature.equals(NAUGHTY) || nature.equals(BRAVE)) {
            atk = (int) (atk * 1.1f);
        }
        if (nature.equals(BOLD) || nature.equals(IMPISH) || nature.equals(LAX) || nature.equals(RELAXED)) {
            def = (int) (def * 1.1f);
        }
        if (nature.equals(MODEST) || nature.equals(MILD) || nature.equals(RASH) || nature.equals(QUIET)) {
            spAtk = (int) (spAtk * 1.1f);
        }
        if (nature.equals(CALM) || nature.equals(GENTLE) || nature.equals(CAREFUL) || nature.equals(SASSY)) {
            spDef = (int) (spDef * 1.1f);
        }
        if (nature.equals(TIMID) || nature.equals(HASTY) || nature.equals(JOLLY) || nature.equals(NAIVE)) {
            speed = (int) (speed * 1.1f);
        }

        if (nature.equals(BOLD) || nature.equals(MODEST) || nature.equals(CALM) || nature.equals(TIMID)) {
            atk = (int) (atk * 0.9f);
        }
        if (nature.equals(LONELY) || nature.equals(MILD) || nature.equals(GENTLE) || nature.equals(HASTY)) {
            def = (int) (def * 0.9f);
        }
        if (nature.equals(ADAMANT) || nature.equals(IMPISH) || nature.equals(CAREFUL) || nature.equals(JOLLY)) {
            spAtk = (int) (spAtk * 0.9f);
        }
        if (nature.equals(NAUGHTY) || nature.equals(LAX) || nature.equals(RASH) || nature.equals(NAIVE)) {
            spDef = (int) (spDef * 0.9f);
        }
        if (nature.equals(BRAVE) || nature.equals(RELAXED) || nature.equals(QUIET) || nature.equals(SASSY)) {
            speed = (int) (speed * 0.9f);
        }

        status.add(0, hp);
        pokemon.getHp().setBattleStatus(hp);
        pokemon.getAttack().setBattleStatus(atk);
        pokemon.getDefense().setBattleStatus(def);
        pokemon.getSpAttack().setBattleStatus(spAtk);
        pokemon.getSpDefense().setBattleStatus(spDef);
        pokemon.getSpeed().setBattleStatus(speed);

        return pokemon;
    }

    public String generateNature() {
        ArrayList<String> natureList = new ArrayList<>();
        Collections.addAll(natureList,
                "Hardy", LONELY, ADAMANT, NAUGHTY, BRAVE,
                            BOLD, "Docile", IMPISH, LAX, RELAXED,
                            MODEST, MILD, "Bashful", RASH, QUIET,
                            CALM, GENTLE, CAREFUL, "Quirky", SASSY,
                            TIMID, HASTY, JOLLY, NAIVE, "Serious");

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
