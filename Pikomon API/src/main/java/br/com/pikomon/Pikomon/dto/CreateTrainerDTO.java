package br.com.pikomon.Pikomon.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTrainerDTO(
        @NotNull String name,
        @NotNull Integer money,
        @NotNull Long pokemonId
) {
}
