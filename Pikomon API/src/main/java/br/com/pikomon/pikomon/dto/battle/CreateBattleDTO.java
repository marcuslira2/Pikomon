package br.com.pikomon.pikomon.dto.battle;

import jakarta.validation.constraints.NotBlank;

public record CreateBattleDTO(
        @NotBlank Integer user_id,
        @NotBlank Integer trainer_id,
        @NotBlank String opponent,
        @NotBlank String location){
}
