package br.com.pikomon.Pikomon.service.pokemon;

import br.com.pikomon.Pikomon.dto.pokemon.PokemonDTO;
import br.com.pikomon.Pikomon.enums.LocationEnum;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.Status;
import br.com.pikomon.Pikomon.persistence.Trainer;
import br.com.pikomon.Pikomon.persistence.User;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import br.com.pikomon.Pikomon.repository.TrainerRepository;
import br.com.pikomon.Pikomon.repository.UserRepository;
import br.com.pikomon.Pikomon.service.LogService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.*;

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

    public PokemonDTO findDTOById(Long id) throws Exception {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new Exception(POKEMON_NOT_FOUND));

        return this.converterToDTO(pokemon);
    }

    public Pokemon findById(Long id) throws Exception {
        return pokemonRepository.findById(id).orElseThrow(() -> new Exception(POKEMON_NOT_FOUND));
    }

    public Pokemon save(Long id, int level, String trainerName) throws Exception {

        Trainer trainer = trainerRepository.findByName(trainerName).orElseThrow(
                () -> new Exception(TRAINER_NOT_FOUND));
        User user = userRepository.findById(trainer.getUserId()).orElseThrow(
                () -> new Exception(USER_NOT_FOUND));

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

    public String deleteById(Long id) throws Exception {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow(() -> new Exception(POKEMON_NOT_FOUND));
        Trainer trainer = trainerRepository.findByName(pokemon.getActualTrainer()).orElseThrow(
                () -> new Exception(TRAINER_NOT_FOUND));
        User user = userRepository.findById(trainer.getUserId()).orElseThrow(
                () -> new Exception(USER_NOT_FOUND));
        pokemon.setDeleted(1);
        pokemon.setDeletedDate(new Date());
        pokemonRepository.save(pokemon);
        String name = !Objects.equals(pokemon.getDisplayName(), "") ? pokemon.getDisplayName() : pokemon.getName();

        String logMsg = pokemon.getActualTrainer() + " deleted a pokemon: " + name;
        this.logService.save(user.getUuid(), trainer.getUuid(), logMsg);

        return name;
    }

    private Pokemon createPokemon(Long id, int level) throws Exception {
        Pokemon pokemonObj = pokemonRepository.findById(id).orElseThrow(
                () -> new Exception(POKEMON_NOT_FOUND));
        Pokemon pokemon = new Pokemon();
        int isShiny = rnd.nextInt(512);


        pokemon.setBase(new Status());
        pokemon.setEv(new Status());
        pokemon.setIv(new Status());
        pokemon.setStatus(new Status());
        pokemon.setOriginStatus(new Status());

        pokemon.setUuid(UUID.randomUUID().toString());
        pokemon.setName(pokemonObj.getName());
        pokemon.setNumber(pokemonObj.getNumber());
        pokemon.setDisplayName(pokemonObj.getDisplayName());
        pokemon.setType1(pokemonObj.getType1());
        pokemon.setEffortType(pokemonObj.getEffortType());
        pokemon.setEffortValue(pokemonObj.getEffortValue());
        pokemon.setBaseExp(pokemonObj.getBaseExp());
        pokemon.getBase().setHp(pokemonObj.getBase().getHp());
        pokemon.getBase().setAtak(pokemonObj.getBase().getAtak());
        pokemon.getBase().setDef(pokemonObj.getBase().getDef());
        pokemon.getBase().setSp_atk(pokemonObj.getBase().getSp_atk());
        pokemon.getBase().setSp_def(pokemonObj.getBase().getSp_def());
        pokemon.getBase().setSpeed(pokemonObj.getBase().getSpeed());
        pokemon.getEv().setHp(0);
        pokemon.getEv().setAtak(0);
        pokemon.getEv().setDef(0);
        pokemon.getEv().setSp_atk(0);
        pokemon.getEv().setSp_def(0);
        pokemon.getEv().setSpeed(0);
        pokemon.getIv().setHp(attributesService.generateIv().get(0));
        pokemon.getIv().setAtak(attributesService.generateIv().get(1));
        pokemon.getIv().setDef(attributesService.generateIv().get(2));
        pokemon.getIv().setSp_atk(attributesService.generateIv().get(3));
        pokemon.getIv().setSp_def(attributesService.generateIv().get(4));
        pokemon.getIv().setSpeed(attributesService.generateIv().get(5));
        pokemon.setNature(attributesService.generateNature());
        pokemon.setLevel(level);
        pokemon.setNextLevel(level);
        pokemon.setExp(64);

        List<Integer> atributes = attributesService.calcAtributes(pokemon);
        pokemon.settingStatus(atributes);
        pokemon.settingOriginStatus(atributes);

        if (pokemonObj.getType2() != null) {
            pokemon.setType2(pokemonObj.getType2());
        }
        pokemon.setShiny(isShiny == 1);
        pokemon.setCreatedDate(new Date());
        pokemon.setDeleted(0);
        pokemon.getMoves().addAll(pokemonObj.getMoves());

        return pokemon;
    }

    public Pokemon createWildPokemon(LocationEnum location) throws Exception {
        int pokemonId = rnd.nextInt(location.getPokemonIdList().length);
        int pokemonLevel = rnd.nextInt(location.getPokemonLevels().length);
        return pokemonRepository.save(createPokemon((long) location.getPokemonIdList()[pokemonId], location.getPokemonLevels()[pokemonLevel]));
    }

    public void updatePokemon(Pokemon pk){
        pokemonRepository.save(pk);
    }


    public void restPokemon(Pokemon pokemon){
        pokemon.getStatus().setHp(pokemon.getOriginStatus().getHp());
        pokemonRepository.save(pokemon);
    }



}
