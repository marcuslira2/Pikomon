package br.com.pikomon.Pikomon.dto.pokemon;

import jakarta.validation.constraints.NotBlank;

public record CreatePokemonDTO(Long id, int level,@NotBlank String trainerUUID) {
}
