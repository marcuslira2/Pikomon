package br.com.pikomon.Pikomon.dto.pokemon;

import br.com.pikomon.Pikomon.enums.TypeEnum;

import java.util.Optional;

public record PokemonDTO(
        Integer number,
        String name,
        TypeEnum type1,
        Optional<TypeEnum> type2
        ) {
}
