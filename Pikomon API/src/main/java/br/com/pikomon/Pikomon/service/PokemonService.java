package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.PokemonDTO;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<PokemonDTO> listAll(){
        List<Pokemon> pokemons = pokemonRepository.findAll();
     return pokemons.stream().map(this::converterToDTO).collect(Collectors.toList());
    }

    private PokemonDTO converterToDTO(Pokemon pokemon){
        return new PokemonDTO(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType1(),
                Optional.ofNullable(pokemon.getType2())
        );
    }
}
