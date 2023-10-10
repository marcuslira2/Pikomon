package br.com.pikomon.Pikomon.dto.pokemon;

import java.util.Optional;

public record PokemonDTO(
        Integer id,
        String name,
        String type1,
        Optional<String> type2
        ) {
}