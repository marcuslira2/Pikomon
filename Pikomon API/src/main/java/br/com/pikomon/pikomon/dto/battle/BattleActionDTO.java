package br.com.pikomon.pikomon.dto.battle;

import jakarta.validation.constraints.NotBlank;

public record BattleActionDTO(
        @NotBlank Integer trainerId,
        @NotBlank Integer moveIndex,
        @NotBlank Long wildPokemonId,
        @NotBlank String battleUUID) {
}
