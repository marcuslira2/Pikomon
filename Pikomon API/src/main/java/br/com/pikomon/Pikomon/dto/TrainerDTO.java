package br.com.pikomon.Pikomon.dto;

import br.com.pikomon.Pikomon.persistence.Pokemon;

import java.util.List;

public record TrainerDTO(String name, Integer money, List<Pokemon> pokemonList) {
}
