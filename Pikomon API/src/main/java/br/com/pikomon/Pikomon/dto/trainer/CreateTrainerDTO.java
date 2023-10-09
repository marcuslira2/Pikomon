package br.com.pikomon.Pikomon.dto.trainer;

import jakarta.validation.constraints.NotBlank;

public record CreateTrainerDTO(
        @NotBlank String name,
        @NotBlank Integer money,
        @NotBlank Long pokemonId
) {
}
