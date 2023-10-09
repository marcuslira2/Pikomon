package br.com.pikomon.Pikomon.service.pokemon;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Service
public class PokemonAttributesService {

    private Random rnd = new Random();

    public List<Integer> calcAtributes(List<Integer> base, List<Integer> ev, List<Integer> iv, Integer level, String nature) {
        List<Integer> status = new ArrayList<>(6);

        Integer hp = (int) Math.floor(0.01 * (2 * base.get(0) + iv.get(0) + Math.floor(0.25 * ev.get(0))) * level + 10);
        Integer atk = (int) (Math.floor(0.01 * (2 * base.get(1) + iv.get(1) + Math.floor(0.25 * ev.get(1)) * level) + 5) * 1f);
        Integer def = (int) (Math.floor(0.01 * (2 * base.get(2) + iv.get(2) + Math.floor(0.25 * ev.get(2)) * level) + 5) * 1f);
        Integer spAtk = (int) (Math.floor(0.01 * (2 * base.get(3) + iv.get(3) + Math.floor(0.25 * ev.get(3)) * level) + 5) * 1f);
        Integer spDef = (int) (Math.floor(0.01 * (2 * base.get(4) + iv.get(4) + Math.floor(0.25 * ev.get(4)) * level) + 5) * 1f);
        Integer speed = (int) (Math.floor(0.01 * (2 * base.get(5) + iv.get(5) + Math.floor(0.25 * ev.get(5)) * level) + 5) * 1f);

        //BUFF
        if (nature.equals("Lonely") || nature.equals("Adamant") || nature.equals("Naughty") || nature.equals("Brave")) {
            atk = (int) (Math.floor(0.01 * (2 * base.get(1) + iv.get(1) + Math.floor(0.25 * ev.get(1)) * level) + 5) * 1.1f);
        }
        if (nature.equals("Bold") || nature.equals("Impish") || nature.equals("Lax") || nature.equals("Relaxed")) {
            def = (int) (Math.floor(0.01 * (2 * base.get(2) + iv.get(2) + Math.floor(0.25 * ev.get(2)) * level) + 5) * 1.1f);
        }
        if (nature.equals("Modest") || nature.equals("Mild") || nature.equals("Rash") || nature.equals("Quiet")) {
            spAtk = (int) (Math.floor(0.01 * (2 * base.get(3) + iv.get(3) + Math.floor(0.25 * ev.get(3)) * level) + 5) * 1.1f);
        }
        if (nature.equals("Calm") || nature.equals("Gentle") || nature.equals("Careful") || nature.equals("Sassy")) {
            spDef = (int) (Math.floor(0.01 * (2 * base.get(4) + iv.get(4) + Math.floor(0.25 * ev.get(4)) * level) + 5) * 1.1f);
        }
        if (nature.equals("Timid") || nature.equals("Hasty") || nature.equals("Jolly") || nature.equals("Naive")) {
            speed = (int) (Math.floor(0.01 * (2 * base.get(5) + iv.get(5) + Math.floor(0.25 * ev.get(5)) * level) + 5) * 1.1f);
        }
        //DEBUFF
        if (nature.equals("Bold") || nature.equals("Modest") || nature.equals("Calm") || nature.equals("Timid")) {
            atk = (int) (Math.floor(0.01 * (2 * base.get(1) + iv.get(1) + Math.floor(0.25 * ev.get(1)) * level) + 5) * 0.9f);
        }
        if (nature.equals("Lonely") || nature.equals("Mild") || nature.equals("Gentle") || nature.equals("Hasty")) {
            def = (int) (Math.floor(0.01 * (2 * base.get(2) + iv.get(2) + Math.floor(0.25 * ev.get(2)) * level) + 5) * 0.9f);
        }
        if (nature.equals("Adamant") || nature.equals("Impish") || nature.equals("Careful") || nature.equals("Jolly")) {
            spAtk = (int) (Math.floor(0.01 * (2 * base.get(3) + iv.get(4) + Math.floor(0.25 * ev.get(3)) * level) + 5) * 0.9f);
        }
        if (nature.equals("Naughty") || nature.equals("Lax") || nature.equals("Rash") || nature.equals("Naive")) {
            spDef = (int) (Math.floor(0.01 * (2 * base.get(4) + iv.get(4) + Math.floor(0.25 * ev.get(4)) * level) + 5) * 0.9f);
        }
        if (nature.equals("Brave") || nature.equals("Relaxed") || nature.equals("Quiet") || nature.equals("Sassy")) {
            speed = (int) (Math.floor(0.01 * (2 * base.get(5) + iv.get(5) + Math.floor(0.25 * ev.get(5)) * level) + 5) * 0.9f);
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
