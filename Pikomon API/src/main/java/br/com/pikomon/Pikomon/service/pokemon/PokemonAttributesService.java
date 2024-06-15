package br.com.pikomon.Pikomon.service.pokemon;

import br.com.pikomon.Pikomon.persistence.Pokemon;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class PokemonAttributesService {

    private Random rnd = new Random();

    public List<Integer> calcAtributes(Pokemon pokemon) {
        List<Integer> status = new ArrayList<>(6);

//        Integer hp = (int) Math.floor(0.01 * (2 * pokemon.getBase().getHp() + pokemon.getIv().getHp() + Math.floor(0.25 * pokemon.getEv().getHp())) * pokemon.getLevel() + 10);
//        Integer atk = (int) Math.floor(0.01 * (2 * pokemon.getBase().getAtak()  + pokemon.getIv().getAtak() + Math.floor(0.25 * pokemon.getEv().getAtak()) * pokemon.getLevel()) + 5);
//        Integer def = (int) Math.floor(0.01 * (2 * pokemon.getBase().getDef()  + pokemon.getIv().getDef() + Math.floor(0.25 * pokemon.getEv().getDef()) * pokemon.getLevel()) + 5);
//        Integer spAtk = (int) Math.floor(0.01 * (2 * pokemon.getBase().getSp_atk()  + pokemon.getIv().getSp_atk() + Math.floor(0.25 * pokemon.getEv().getSp_atk()) * pokemon.getLevel()) + 5);
//        Integer spDef = (int) Math.floor(0.01 * (2 * pokemon.getBase().getSp_def()  + pokemon.getIv().getSp_def() + Math.floor(0.25 * pokemon.getEv().getSp_def()) * pokemon.getLevel()) + 5);
//        Integer speed = (int) Math.floor(0.01 * (2 * pokemon.getBase().getSpeed()  + pokemon.getIv().getSpeed() + Math.floor(0.25 * pokemon.getEv().getSpeed()) * pokemon.getLevel()) + 5);
        Integer hp = (((2*pokemon.getBase().getHp()+pokemon.getIv().getHp()+(pokemon.getEv().getHp()/4))*pokemon.getLevel()/100+10));
        Integer atk = (((2 * pokemon.getBase().getAtak()+pokemon.getIv().getAtak() + (pokemon.getEv().getAtak()/4)) * pokemon.getLevel()/100 + 5));
        Integer def = (((2 * pokemon.getBase().getDef()+pokemon.getIv().getDef() + (pokemon.getEv().getDef()/4)) * pokemon.getLevel()/100 + 5));
        Integer spAtk = (((2 * pokemon.getBase().getSp_atk()+pokemon.getIv().getSp_atk() + (pokemon.getEv().getSp_atk()/4)) * pokemon.getLevel()/100 + 5));
        Integer spDef = (((2 * pokemon.getBase().getSp_def()+pokemon.getIv().getSp_def() + (pokemon.getEv().getSp_def()/4)) * pokemon.getLevel()/100 + 5));
        Integer speed = (((2 * pokemon.getBase().getSpeed()+pokemon.getIv().getSpeed() + (pokemon.getEv().getSpeed()/4)) * pokemon.getLevel()/100 + 5));

        String nature = pokemon.getNature();

        //BUFF
        if (nature.equals("Lonely") || nature.equals("Adamant") || nature.equals("Naughty") || nature.equals("Brave")) {
            atk = (int) (atk * 1.1f);
        }
        if (nature.equals("Bold") || nature.equals("Impish") || nature.equals("Lax") || nature.equals("Relaxed")) {
            def = (int) (def * 1.1f);
        }
        if (nature.equals("Modest") || nature.equals("Mild") || nature.equals("Rash") || nature.equals("Quiet")) {
            spAtk = (int) (spAtk * 1.1f);
        }
        if (nature.equals("Calm") || nature.equals("Gentle") || nature.equals("Careful") || nature.equals("Sassy")) {
            spDef = (int) (spDef * 1.1f);
        }
        if (nature.equals("Timid") || nature.equals("Hasty") || nature.equals("Jolly") || nature.equals("Naive")) {
            speed = (int) (speed * 1.1f);
        }
        //DEBUFF
        if (nature.equals("Bold") || nature.equals("Modest") || nature.equals("Calm") || nature.equals("Timid")) {
            atk = (int) (atk * 0.9f);
        }
        if (nature.equals("Lonely") || nature.equals("Mild") || nature.equals("Gentle") || nature.equals("Hasty")) {
            def = (int) (def * 0.9f);
        }
        if (nature.equals("Adamant") || nature.equals("Impish") || nature.equals("Careful") || nature.equals("Jolly")) {
            spAtk = (int) (spAtk * 0.9f);
        }
        if (nature.equals("Naughty") || nature.equals("Lax") || nature.equals("Rash") || nature.equals("Naive")) {
            spDef = (int) (spDef * 0.9f);
        }
        if (nature.equals("Brave") || nature.equals("Relaxed") || nature.equals("Quiet") || nature.equals("Sassy")) {
            speed = (int) (speed * 0.9f);
        }

        status.add(0, hp);
        status.add(1, atk);
        status.add(2, def);
        status.add(3, spAtk);
        status.add(4, spDef);
        status.add(5, speed);

        return status;
    }

    public String generateNature() {
        ArrayList<String> natureList = new ArrayList<>();
        Collections.addAll(natureList,
                "Hardy", "Lonely", "Adamant", "Naughty", "Brave",
                "Bold", "Docile", "Impish", "Lax", "Relaxed",
                "Modest", "Mild", "Bashful", "Rash", "Quiet",
                "Calm", "Gentle", "Careful", "Quirky", "Sassy",
                "Timid", "Hasty", "Jolly", "Naive", "Serious");

        Integer selectNature = this.rnd.nextInt(natureList.size());
        return natureList.get(selectNature);
    }

    public List<Integer> generateEv() {
        List<Integer> evList = new ArrayList<>(6);
        evList.add(0, 0);
        evList.add(1, 0);
        evList.add(2, 0);
        evList.add(3, 0);
        evList.add(4, 0);
        evList.add(5, 0);
        return evList;
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
