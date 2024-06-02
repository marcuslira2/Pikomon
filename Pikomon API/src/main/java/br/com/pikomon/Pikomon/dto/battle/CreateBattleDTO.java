package br.com.pikomon.Pikomon.dto.battle;

import br.com.pikomon.Pikomon.enums.BattleResultEnum;
import br.com.pikomon.Pikomon.enums.BattleStatusEnum;
import jakarta.validation.constraints.NotBlank;

public record CreateBattleDTO(
        @NotBlank Integer user_id,
        @NotBlank Integer trainer_id,
        @NotBlank String opponent,
        @NotBlank String location){
}
