package br.com.pikomon.Pikomon.dto.trainer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTrainerDTO(
        @NotBlank String name,
        @NotNull Integer money,
        @NotNull Long pokemonId,
        @NotNull Integer userID
) {
}
