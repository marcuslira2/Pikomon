package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.battle.BattleActionDTO;
import br.com.pikomon.Pikomon.dto.battle.BattleActionValidateDTO;
import br.com.pikomon.Pikomon.dto.battle.CloseBattleDTO;
import br.com.pikomon.Pikomon.dto.battle.CreateBattleDTO;
import br.com.pikomon.Pikomon.dto.trainer.TrainerDTO;
import br.com.pikomon.Pikomon.enums.BattleResultEnum;
import br.com.pikomon.Pikomon.enums.BattleStatusEnum;
import br.com.pikomon.Pikomon.enums.LocationEnum;
import br.com.pikomon.Pikomon.enums.OpponentTypeEnum;
import br.com.pikomon.Pikomon.persistence.*;
import br.com.pikomon.Pikomon.repository.BattleRepository;
import br.com.pikomon.Pikomon.service.pokemon.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class BattleService {


    @Autowired
    private BattleRepository battleRepository;

    @Autowired
    private PokemonService pokemonService;

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private LogService logService;

    @Autowired
    private MoveService moveService;

    @Autowired
    private CalcService calcService;


    public Battle create(CreateBattleDTO dto) throws Exception {
        Battle battle = new Battle();

        battle.setOpponent(OpponentTypeEnum.valueOf(dto.opponent()));
        battle.setLocation(LocationEnum.valueOf(dto.location()));
        if (OpponentTypeEnum.valueOf(dto.opponent()).equals(OpponentTypeEnum.WILD_POKEMON)){
            pokemonService.createWildPokemon(LocationEnum.valueOf(dto.location()));
        }
        battle.setTrainer_id(dto.trainer_id());
        battle.setUser_id(dto.user_id());
        battle.setStatus(BattleStatusEnum.START);
        Battle save = battleRepository.save(battle);
        return save;
    }


    public Battle close(CloseBattleDTO dto){
        Battle battle = battleRepository.findById(dto.id()).orElseThrow(()-> new RuntimeException("Battle not found"));

        if (battle.getStatus().equals(BattleStatusEnum.FINISHED)){
            throw new RuntimeException("This battle alraedy finished!");
        }else {
            battle.setStatus(BattleStatusEnum.FINISHED);
            battle.setResult(BattleResultEnum.valueOf(dto.result()));
            return battle;
        }
    }

    public Log makeAMove (BattleActionDTO dto) throws Exception {
        BattleActionValidateDTO validateBattle = validate(dto);
        Pokemon pokemon = validateBattle.trainer().pokemonList().get(0);
        Pokemon wildPokemon = validateBattle.wildPokemon();
        Move moveUsed = validateBattle.moveUsed();

        // passo a passo = pokemon aliado golpeia primeiro sempre,
        // depois pokemon inimigo, gera o log e persiste tudo,
        // tanto o dano causado ao pokemon aliado quanto ao pokemon inimigo
        // quanto o log da batalha
        // Calculo de dano feito levando em conta apenas o primeiro tipo, corrigir isso futuramente

        Integer damage = calcService.calcDamage(pokemon, wildPokemon, moveUsed);


        return null;
    }




    private BattleActionValidateDTO validate(BattleActionDTO dto) throws Exception {
        TrainerDTO trainer = trainerService.findById(dto.trainerId());
        Pokemon wildPokemon = pokemonService.findById(dto.wildPokemonId());
        Battle battle = battleRepository.findByuuid(dto.battleUUID());
        if (battle.getStatus().equals(BattleStatusEnum.FINISHED)){
            throw new RuntimeException("The battle was finished");
        }
        Pokemon allyPokemon = trainer.pokemonList().get(0);
        Move move = moveService.findMoveByName(allyPokemon.getMoves().get(0).getMoveName());
        BattleActionValidateDTO battleAction = new BattleActionValidateDTO(
                trainer,
                wildPokemon,
                move,
                battle
        );

        return battleAction;

    }


}
