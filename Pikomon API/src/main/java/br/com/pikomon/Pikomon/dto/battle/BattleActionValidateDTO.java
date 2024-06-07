package br.com.pikomon.Pikomon.dto.battle;

import br.com.pikomon.Pikomon.dto.trainer.TrainerDTO;
import br.com.pikomon.Pikomon.persistence.Battle;
import br.com.pikomon.Pikomon.persistence.Move;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.persistence.Trainer;

public record BattleActionValidateDTO(Trainer trainer, Pokemon wildPokemon, Move moveUsed, Battle battle) {
}
