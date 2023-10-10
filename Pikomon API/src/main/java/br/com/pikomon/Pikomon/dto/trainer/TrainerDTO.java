package br.com.pikomon.Pikomon.dto.trainer;

import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.User;

import java.util.List;

public record TrainerDTO(String name, Integer money, List<Pokemon> pokemonList, Integer userId) {
}
