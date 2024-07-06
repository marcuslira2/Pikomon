package br.com.pikomon.pikomon.service.pokemon;

import br.com.pikomon.pikomon.dto.pokemon.PokemonDTO;
import br.com.pikomon.pikomon.enums.LocationEnum;
import br.com.pikomon.pikomon.persistence.Pokemon;
import br.com.pikomon.pikomon.persistence.Status;
import br.com.pikomon.pikomon.persistence.Trainer;
import br.com.pikomon.pikomon.persistence.User;
import br.com.pikomon.pikomon.repository.PokemonRepository;
import br.com.pikomon.pikomon.repository.TrainerRepository;
import br.com.pikomon.pikomon.repository.UserRepository;
import br.com.pikomon.pikomon.service.LogService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    private final PokemonAttributesService attributesService;

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    private final LogService logService;

    private final Random rnd = new SecureRandom();

    private static final String POKEMON_NOT_FOUND = "Pokemon not found";
    private static final String TRAINER_NOT_FOUND = "Trainer not found";
    private static final String USER_NOT_FOUND = "User not found";

    public PokemonService(PokemonRepository pokemonRepository, PokemonAttributesService attributesService, TrainerRepository trainerRepository, UserRepository userRepository, LogService logService) {
        this.pokemonRepository = pokemonRepository;
        this.attributesService = attributesService;
        this.trainerRepository = trainerRepository;
        this.userRepository = userRepository;
        this.logService = logService;
    }

    public List<PokemonDTO> listAll() {
        return pokemonRepository.findAll().stream()
                .filter(pokemon -> pokemon.getDeleted() == 0).toList()
                .stream().map(this::converterToDTO).toList();
    }

    private PokemonDTO converterToDTO(Pokemon pokemon) {
        return new PokemonDTO(
                pokemon.getNumber(),
                pokemon.getName(),
                pokemon.getType1(),
                Optional.ofNullable(pokemon.getType2())
        );
    }

    public PokemonDTO findDTOById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new NoSuchElementException(POKEMON_NOT_FOUND));

        return this.converterToDTO(pokemon);
    }

    public Pokemon findById(Long id) {
        return pokemonRepository.findById(id).orElseThrow(() -> new NoSuchElementException(POKEMON_NOT_FOUND));
    }

    public Pokemon save(Long id, int level, String trainerName) {

        Trainer trainer = trainerRepository.findByName(trainerName).orElseThrow(
                () -> new NoSuchElementException(TRAINER_NOT_FOUND));
        User user = userRepository.findById(trainer.getUserId()).orElseThrow(
                () -> new NoSuchElementException(USER_NOT_FOUND));

        Pokemon pokemon = createPokemon(id, level);
        pokemon.setTrainerUUID(trainer.getUuid());
        pokemon.setActualTrainer(trainer.getName());
        pokemon.setOriginalTrainer(trainer.getName());

        if (trainerName !=null){
            String logMsg = trainerName + " registered a new pokemon: " + pokemon.getName();
            this.logService.save(user.getUuid(), trainer.getUuid(), logMsg);
        }

        return pokemonRepository.save(pokemon);
    }

    public String deleteById(Long id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new NoSuchElementException(POKEMON_NOT_FOUND));
        Trainer trainer = trainerRepository.findByName(pokemon.getActualTrainer()).orElseThrow(
                () -> new NoSuchElementException(TRAINER_NOT_FOUND));
        User user = userRepository.findById(trainer.getUserId()).orElseThrow(
                () -> new NoSuchElementException(USER_NOT_FOUND));
        pokemon.setDeleted(1);
        pokemon.setDeletedDate(new Date());
        pokemonRepository.save(pokemon);
        String name = !Objects.equals(pokemon.getDisplayName(), "") ? pokemon.getDisplayName() : pokemon.getName();

        String logMsg = pokemon.getActualTrainer() + " deleted a pokemon: " + name;
        this.logService.save(user.getUuid(), trainer.getUuid(), logMsg);

        return name;
    }

    private Pokemon createPokemon(Long id, int level) {
        Pokemon pokemonObj = pokemonRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException(POKEMON_NOT_FOUND));
        Pokemon pokemon = new Pokemon();
        int isShiny = rnd.nextInt(512);

        pokemon.setUuid(UUID.randomUUID().toString());
        pokemon.setName(pokemonObj.getName());
        pokemon.setNumber(pokemonObj.getNumber());
        pokemon.setDisplayName(pokemonObj.getDisplayName());
        pokemon.setType1(pokemonObj.getType1());
        pokemon.setBaseExp(pokemonObj.getBaseExp());
        Status hp = new Status();
        hp.setName(pokemonObj.getHp().getName());
        hp.setEffort(pokemonObj.getHp().getEffort());
        hp.setBaseStatus(pokemonObj.getHp().getBaseStatus());
        hp.setEv(0);
        pokemon.setHp(hp);
        Status attack = new Status();
        attack.setName(pokemonObj.getAttack().getName());
        attack.setEffort(pokemonObj.getAttack().getEffort());
        attack.setBaseStatus(pokemonObj.getAttack().getBaseStatus());
        attack.setEv(0);
        pokemon.setAttack(attack);
        Status defense = new Status();
        defense.setName(pokemonObj.getDefense().getName());
        defense.setEffort(pokemonObj.getDefense().getEffort());
        defense.setBaseStatus(pokemonObj.getDefense().getBaseStatus());
        defense.setEv(0);
        pokemon.setDefense(defense);
        Status spAttack = new Status();
        spAttack.setName(pokemonObj.getSpAttack().getName());
        spAttack.setEffort(pokemonObj.getSpAttack().getEffort());
        spAttack.setBaseStatus(pokemonObj.getAttack().getBaseStatus());
        spAttack.setEv(0);
        pokemon.setSpAttack(spAttack);
        Status spDefense = new Status();
        spDefense.setName(pokemonObj.getSpDefense().getName());
        spDefense.setEffort(pokemonObj.getSpDefense().getEffort());
        spDefense.setBaseStatus(pokemonObj.getSpDefense().getBaseStatus());
        spDefense.setEv(0);
        pokemon.setSpDefense(spDefense);
        Status speed = new Status();
        speed.setName(pokemonObj.getSpeed().getName());
        speed.setEffort(pokemonObj.getSpeed().getEffort());
        speed.setBaseStatus(pokemonObj.getSpeed().getBaseStatus());
        speed.setEv(0);
        pokemon.setSpeed(speed);
        pokemon.getHp().setIv(attributesService.generateIv().get(0));
        pokemon.getAttack().setIv(attributesService.generateIv().get(1));
        pokemon.getDefense().setIv(attributesService.generateIv().get(2));
        pokemon.getSpAttack().setIv(attributesService.generateIv().get(3));
        pokemon.getSpDefense().setIv(attributesService.generateIv().get(4));
        pokemon.getSpeed().setIv(attributesService.generateIv().get(5));
        pokemon.setNature(attributesService.generateNature());
        pokemon.setLevel(level);
        pokemon.setNextLevel(level);
        pokemon.setExp(64);

        pokemon = attributesService.calcBattleStatus(pokemon);

        if (pokemonObj.getType2() != null) {
            pokemon.setType2(pokemonObj.getType2());
        }
        pokemon.setShiny(isShiny == 1);
        pokemon.setCreatedDate(new Date());
        pokemon.setDeleted(0);
        pokemon.getMoves().addAll(pokemonObj.getMoves());

        return pokemon;
    }

    public Pokemon createWildPokemon(LocationEnum location) {
        int pokemonId = rnd.nextInt(location.getPokemonIdList().length);
        int pokemonLevel = rnd.nextInt(location.getPokemonLevels().length);
        return pokemonRepository.save(createPokemon((long) location.getPokemonIdList()[pokemonId], location.getPokemonLevels()[pokemonLevel]));
    }

    public void updatePokemon(Pokemon pk){
        pokemonRepository.save(pk);
    }



}
