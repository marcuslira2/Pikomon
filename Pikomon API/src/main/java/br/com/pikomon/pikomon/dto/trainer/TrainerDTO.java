package br.com.pikomon.pikomon.dto.trainer;

import br.com.pikomon.pikomon.persistence.Pokemon;

import java.util.List;

public record TrainerDTO(String name, Integer money, List<Pokemon> pokemonList, Integer userId) {
}
