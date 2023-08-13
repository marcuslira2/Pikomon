package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.PokemonDTO;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.Trainer;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import br.com.pikomon.Pikomon.repository.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    public List<PokemonDTO> listAll() {
        List<Pokemon> pokemons = pokemonRepository.findAll();
        return pokemons.stream().map(this::converterToDTO).collect(Collectors.toList());
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

    public Pokemon create(int pokeId, int level, String trainerName) {
        Optional<Pokemon> pokeObject = this.findById(pokeId);

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
        }
        return pokemonRepository.save(pokemon);
    }

    public void deleteById(Integer id) {
        pokemonRepository.deleteById(id);
    }
}
