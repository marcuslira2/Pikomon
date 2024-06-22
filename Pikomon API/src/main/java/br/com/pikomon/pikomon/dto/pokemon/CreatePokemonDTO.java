package br.com.pikomon.pikomon.dto.pokemon;

import jakarta.validation.constraints.NotBlank;

public record CreatePokemonDTO(@NotBlank(message = "{name.not.null.or.blank}") Long id, int level,@NotBlank String trainerUUID) {
}
