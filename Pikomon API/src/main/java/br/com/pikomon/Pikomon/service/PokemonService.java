package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.PokemonDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    private Random rnd = new Random();

    private static final Logger log = LoggerFactory.getLogger(PokemonService.class);


    public List<PokemonDTO> listAll() {
        return pokemonRepository.findAll().stream()
                .filter(pokemon -> pokemon.getDeleted() == 0).toList()
                .stream().map(this::converterToDTO).toList();
    }

    private PokemonDTO converterToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType1(),
                Optional.ofNullable(pokemon.getType2())
        );
    }

    public ResponseEntity<?> findById(Long id) throws ObjectNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        if (pokemon.getDeleted()==1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon deleted");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(pokemon);
    }

    public Pokemon save(Long id, int level, String trainerName) throws ObjectNotFoundException {
        Pokemon pokemonObj = pokemonRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        Pokemon pokemon = new Pokemon();
        Integer isShiny = rnd.nextInt(500);
        pokemon.setName(pokemonObj.getName());
        pokemon.setId(pokemonObj.getId());
        pokemon.setDisplayName(pokemonObj.getDisplayName());
        pokemon.setType1(pokemonObj.getType1());
        if (pokemonObj.getType2()!=null){
        pokemon.setType2(pokemonObj.getType2());
        }
        pokemon.setEffortType(pokemonObj.getEffortType());
        pokemon.setEffortValue(pokemonObj.getEffortValue());
        pokemon.setBaseExp(pokemonObj.getBaseExp());
        pokemon.setActualTrainer(trainerName);
        pokemon.setOriginalTrainer(trainerName);
        pokemon.getBase().addAll(pokemonObj.getBase());
        pokemon.getEv().addAll(generateEv());
        pokemon.getIv().addAll(generateIv());
        pokemon.setNature(this.generateNature());
        pokemon.setLevel(level);
        pokemon.getStatus().addAll(
                this.calcAtributes(pokemon.getBase(), pokemon.getEv(), pokemon.getIv(), pokemon.getLevel(), pokemon.getNature()));
        pokemon.setShiny(isShiny == 1);
        pokemon.setCreatedDate(new Date());
        pokemon.setDeleted(0);
        return pokemonRepository.save(pokemon);

    }

    private List<Integer> calcAtributes(List<Integer> base, List<Integer> ev, List<Integer> iv, Integer level, String nature) {
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

    private List<Integer> generateEv() {
        List<Integer> evList = new ArrayList<>(6);
        evList.add(0, 0);
        evList.add(1, 0);
        evList.add(2, 0);
        evList.add(3, 0);
        evList.add(4, 0);
        evList.add(5, 0);
        return evList;
    }

    private List<Integer> generateIv() {
        List<Integer> ivList = new ArrayList<>(6);

        ivList.add(0, rnd.nextInt(31));
        ivList.add(1, rnd.nextInt(31));
        ivList.add(2, rnd.nextInt(31));
        ivList.add(3, rnd.nextInt(31));
        ivList.add(4, rnd.nextInt(31));
        ivList.add(5, rnd.nextInt(31));
        return ivList;
    }

    public ResponseEntity<?> deleteById(Long id) throws ObjectNotFoundException {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(ObjectNotFoundException::new);
        if (pokemon.getDeleted()==1){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pokemon already gone");
        }
        pokemon.setDeleted(1);
        pokemon.setDeletedDate(new Date());
        pokemonRepository.save(pokemon);
        String name = !Objects.equals(pokemon.getDisplayName(), "") ? pokemon.getDisplayName() : pokemon.getName();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Goodbye " + name);
    }
}
