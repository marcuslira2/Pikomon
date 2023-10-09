package br.com.pikomon.Pikomon.service.pokemon;

import br.com.pikomon.Pikomon.dto.pokemon.PokemonDTO;
import br.com.pikomon.Pikomon.infra.exceptions.ObjectNotFoundException;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    private final PokemonAttributesService attributesService;

    private Random rnd = new Random();

    private static final Logger log = LoggerFactory.getLogger(PokemonService.class);

    public PokemonService(PokemonRepository pokemonRepository, PokemonAttributesService attributesService) {
        this.pokemonRepository = pokemonRepository;
        this.attributesService = attributesService;
    }

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
        Integer isShiny = rnd.nextInt(512);
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
        pokemon.getEv().addAll(attributesService.generateEv());
        pokemon.getIv().addAll(attributesService.generateIv());
        pokemon.setNature(attributesService.generateNature());
        pokemon.setLevel(level);
        pokemon.getStatus().addAll(
                attributesService.calcAtributes(pokemon.getBase(), pokemon.getEv(), pokemon.getIv(), pokemon.getLevel(), pokemon.getNature()));
        pokemon.setShiny(isShiny == 1);
        pokemon.setCreatedDate(new Date());
        pokemon.setDeleted(0);
        return pokemonRepository.save(pokemon);
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
