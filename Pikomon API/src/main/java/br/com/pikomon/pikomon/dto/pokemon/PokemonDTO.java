package br.com.pikomon.pikomon.dto.pokemon;

import br.com.pikomon.pikomon.enums.TypeEnum;

import java.util.Optional;

public record PokemonDTO(
        Integer number,
        String name,
        TypeEnum type1,
        Optional<TypeEnum> type2
        ) {
}
