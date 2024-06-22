package br.com.pikomon.pikomon.dto.battle;

import br.com.pikomon.pikomon.persistence.Battle;
import br.com.pikomon.pikomon.persistence.Move;
import br.com.pikomon.pikomon.persistence.Pokemon;
import br.com.pikomon.pikomon.persistence.Trainer;

public record BattleActionValidateDTO(Trainer trainer, Pokemon wildPokemon, Move moveUsed, Battle battle) {
}
