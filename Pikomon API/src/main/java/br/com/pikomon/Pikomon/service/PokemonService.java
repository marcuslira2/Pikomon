package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.PokemonDTO;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import br.com.pikomon.Pikomon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public List<PokemonDTO> listAll() {
        return pokemonRepository.findAll().stream()
                .filter(pokemon -> !pokemon.getDeleted()).toList()
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

    public Optional<Pokemon> findById(Integer id) {

        return pokemonRepository.findById(id);
    }

    public Pokemon save(int id, int level, String trainerName) {
        Optional<Pokemon> pokeObject = this.findById(id);
        Random rd = new Random();

        Pokemon pokemon = new Pokemon();
        if (pokeObject.isPresent()){
            pokemon.setName(pokeObject.get().getName());
            pokemon.setActualTrainer(trainerName);
            pokemon.setOriginalTrainer(trainerName);
            pokemon.setDisplayName(pokeObject.get().getDisplayName());
            pokemon.setId(pokeObject.get().getId());
            pokemon.setLevel(level);
            pokemon.setType1(pokeObject.get().getType1());
            pokemon.setType2(pokeObject.get().getType2());
            pokemon.setBaseExp(pokeObject.get().getBaseExp());
            pokemon.setBaseSpeed(pokeObject.get().getBaseSpeed());
            pokemon.setBaseSpDef(pokeObject.get().getBaseSpDef());
            pokemon.setBaseSpAtk(pokeObject.get().getBaseSpAtk());
            pokemon.setBaseHp(pokeObject.get().getBaseHp());
            pokemon.setBaseAtk(pokeObject.get().getBaseAtk());
            pokemon.setBaseDef(pokeObject.get().getBaseDef());
            pokemon.addAll(pokeObject.get().getMoves());
            pokemon.setShiny(rd.nextBoolean());
            pokemon.setCreatedDate(new Date());
            pokemon.setDeleted(false);
        }

        return pokemonRepository.save(pokemon);

    }

    public void deleteById(Integer id) {
        Optional<Pokemon> pokemon = this.findById(id);
        if (pokemon.isPresent()){
            pokemon.get().setDeleted(true);
            pokemon.get().setDeletedDate(new Date());
            pokemonRepository.save(pokemon.get());
        }

    }
}
