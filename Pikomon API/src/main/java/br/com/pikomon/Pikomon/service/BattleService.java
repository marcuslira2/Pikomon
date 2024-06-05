package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.dto.battle.CloseBattleDTO;
import br.com.pikomon.Pikomon.dto.battle.CreateBattleDTO;
import br.com.pikomon.Pikomon.enums.BattleResultEnum;
import br.com.pikomon.Pikomon.enums.BattleStatusEnum;
import br.com.pikomon.Pikomon.enums.LocationEnum;
import br.com.pikomon.Pikomon.enums.OpponentTypeEnum;
import br.com.pikomon.Pikomon.persistence.Battle;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import br.com.pikomon.Pikomon.repository.BattleRepository;
import br.com.pikomon.Pikomon.repository.PokemonRepository;
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




}
