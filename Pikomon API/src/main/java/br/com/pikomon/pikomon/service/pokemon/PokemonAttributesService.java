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

    private final String adamant = "Adamant";
    private final String naughty = "Naughty";
    private final String lonely = "Lonely";
    private final String brave = "Brave";
    private final String bold = "Bold";
    private final String impish = "Impish";
    private final String lax = "Lax";
    private final String relaxed = "Relaxed";
    private final String modest = "Modest";
    private final String mild = "Mild";
    private final String rash = "Rash";
    private final String quiet = "Quiet";
    private final String calm = "Calm";
    private final String gentle = "Gentle";
    private final String careful = "Careful";
    private final String sassy = "Sassy";
    private final String timid = "Timid";
    private final String hasty = "Hasty";
    private final String jolly = "Jolly";
    private final String naive = "Naive";




    public Pokemon calcBattleStatus(Pokemon pokemon) {

        int hp = ((2*pokemon.getHp().getBaseStatus()+pokemon.getHp().getIv()+(pokemon.getHp().getEv()/4))*pokemon.getLevel()/100+10);
        int atk = ((2 * pokemon.getAttack().getBaseStatus()+pokemon.getAttack().getIv()+ (pokemon.getAttack().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int def = ((2 * pokemon.getDefense().getBaseStatus()+pokemon.getDefense().getIv() + (pokemon.getDefense().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int spAtk = ((2 * pokemon.getSpAttack().getBaseStatus()+pokemon.getSpAttack().getIv() + (pokemon.getSpAttack().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int spDef = ((2 * pokemon.getSpDefense().getBaseStatus()+pokemon.getSpDefense().getIv() + (pokemon.getSpDefense().getEv()/4)) * pokemon.getLevel()/100 + 5);
        int speed = ((2 * pokemon.getSpeed().getBaseStatus()+pokemon.getSpeed().getIv() + (pokemon.getSpeed().getEv()/4)) * pokemon.getLevel()/100 + 5);

        String nature = pokemon.getNature();

        //BUFF
        if (nature.equals(lonely) || nature.equals(adamant) || nature.equals(naughty) || nature.equals(brave)) {
            atk = (int) (atk * 1.1f);
        }
        if (nature.equals(bold) || nature.equals(impish) || nature.equals(lax) || nature.equals(relaxed)) {
            def = (int) (def * 1.1f);
        }
        if (nature.equals(modest) || nature.equals(mild) || nature.equals(rash) || nature.equals(quiet)) {
            spAtk = (int) (spAtk * 1.1f);
        }
        if (nature.equals(calm) || nature.equals(gentle) || nature.equals(careful) || nature.equals(sassy)) {
            spDef = (int) (spDef * 1.1f);
        }
        if (nature.equals(timid) || nature.equals(hasty) || nature.equals(jolly) || nature.equals(naive)) {
            speed = (int) (speed * 1.1f);
        }

        if (nature.equals(bold) || nature.equals(modest) || nature.equals(calm) || nature.equals(timid)) {
            atk = (int) (atk * 0.9f);
        }
        if (nature.equals(lonely) || nature.equals(mild) || nature.equals(gentle) || nature.equals(hasty)) {
            def = (int) (def * 0.9f);
        }
        if (nature.equals(adamant) || nature.equals(impish) || nature.equals(careful) || nature.equals(jolly)) {
            spAtk = (int) (spAtk * 0.9f);
        }
        if (nature.equals(naughty) || nature.equals(lax) || nature.equals(rash) || nature.equals(naive)) {
            spDef = (int) (spDef * 0.9f);
        }
        if (nature.equals(brave) || nature.equals(relaxed) || nature.equals(quiet) || nature.equals(sassy)) {
            speed = (int) (speed * 0.9f);
        }

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
                "Hardy", lonely, adamant, naughty, brave,
                bold, "Docile", impish, lax, relaxed,
                modest, mild, "Bashful", rash, quiet,
                calm, gentle, careful, "Quirky", sassy,
                timid, hasty, jolly, naive, "Serious");

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
