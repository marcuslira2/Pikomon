package br.com.pikomon.Pikomon.dto.battle;

import jakarta.validation.constraints.NotBlank;

public record CloseBattleDTO(
        @NotBlank Long id,
        @NotBlank String status,
        @NotBlank String result) {
}
