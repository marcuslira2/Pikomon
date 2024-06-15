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
import br.com.pikomon.Pikomon.service.pokemon.PokemonAttributesService;
import br.com.pikomon.Pikomon.service.pokemon.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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

    @Autowired
    private PokemonAttributesService attributesService;

    private final Random rnd = new SecureRandom();


    public Battle create(CreateBattleDTO dto) throws Exception {
        Battle battle = new Battle();

        battle.setOpponent(OpponentTypeEnum.valueOf(dto.opponent()));
        battle.setLocation(LocationEnum.valueOf(dto.location()));
        Pokemon enemy = pokemonService.createWildPokemon(LocationEnum.valueOf(dto.location()));

        battle.setTrainer_id(dto.trainer_id());
        battle.setUser_id(dto.user_id());
        battle.setStatus(BattleStatusEnum.START);
        battle.setUuid(UUID.randomUUID().toString());
        battle.setEnemy_id(enemy.getId());
        Battle save = battleRepository.save(battle);
        return save;
    }

    public Battle findById(Long id){
        return battleRepository.findById(id).orElseThrow(()-> new RuntimeException("Battle not found."));
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

    public String makeAMove (BattleActionDTO dto) throws Exception {
        BattleActionValidateDTO validateBattle = validate(dto);
        Battle battle = battleRepository.findByuuid(dto.battleUUID());
        Pokemon pokemon = validateBattle.trainer().getPokemons().get(0);
        Pokemon wildPokemon = validateBattle.wildPokemon();
        Move moveUsed = validateBattle.moveUsed();
        Move wildMoveUsed = moveService.findMoveByName(wildPokemon.getMoves().get(rnd.nextInt(3)).getMoveName());

        Integer damageAlly = calcService.calcDamage(pokemon, wildPokemon, moveUsed);
        Integer damageWild = calcService.calcDamage(wildPokemon, pokemon, wildMoveUsed);

        Integer newHpWild = wildPokemon.getStatus().getHp() - damageAlly;
        Integer newHpAlly = pokemon.getStatus().getHp() - damageWild;


        wildPokemon.getStatus().setHp(newHpWild);
        if (wildPokemon.getStatus().getHp()< 0){
            wildPokemon.getStatus().setHp(0);
        }
        pokemon.getStatus().setHp(newHpAlly);
        if (pokemon.getStatus().getHp()<0){
            pokemon.getStatus().setHp(0);
        }

        String log = "";

        if (pokemon.getStatus().getHp()>0 && wildPokemon.getStatus().getHp() > 0){
            log = pokemon.getName()+" used "+pokemon.getMoves().get(0).getMoveName()+".\n" +
                    "Deal "+damageAlly+" on "+wildPokemon.getName()+". remains hp: "+wildPokemon.getStatus().getHp()+"\n" +
                    "Enemy " + wildPokemon.getName()+" used "+ wildMoveUsed.getName()+"\n" +
                    "Deal "+damageWild+" on "+ pokemon.getName()+". remains hp: "+pokemon.getStatus().getHp();
            pokemonService.updatePokemon(pokemon);
            pokemonService.updatePokemon(wildPokemon);
        }else if (pokemon.getStatus().getHp() > 0 || wildPokemon.getStatus().getHp() ==0){

            battle.setStatus(BattleStatusEnum.FINISHED);
            Integer nextLevel = pokemon.getNextLevel();
            List<Integer> oldAtributes = attributesService.calcAtributes(pokemon);
            pokemon = calcService.calcExp(pokemon, wildPokemon);
            log = "Battle has finished! you win";
            if (pokemon.getExp()>nextLevel){
                List<Integer> atributes = attributesService.calcAtributes(pokemon);
                pokemon.settingStatus(atributes);
                pokemon.settingOriginStatus(atributes);
                log +="\n "+pokemon.getName()+" grew to LV."+pokemon.getLevel()+"!";
                log+= "\n MAX. HP: + "+(atributes.get(0)-oldAtributes.get(0));
                log+= "\n ATTACK: + "+(atributes.get(1)-oldAtributes.get(1));
                log+= "\n DEFENSE: + "+(atributes.get(2)-oldAtributes.get(2));
                log+= "\n SP. ATAK: + "+(atributes.get(3)-oldAtributes.get(3));
                log+= "\n SP. DEF: + "+(atributes.get(4)-oldAtributes.get(4));
                log+= "\n SPEED: + "+(atributes.get(5)-oldAtributes.get(5));
                log+= "\n\n NEW STATUS: ";
                log+= "\n MAX. HP: "+atributes.get(0);
                log+= "\n ATTACK: "+atributes.get(1);
                log+= "\n DEFENSE: "+atributes.get(2);
                log+= "\n SP. ATAK: "+atributes.get(3);
                log+= "\n SP. DEF: "+atributes.get(4);
                log+= "\n SPEED: "+atributes.get(5);
                pokemonService.updatePokemon(pokemon);
            }
            pokemonService.restPokemon(pokemon);

            battleRepository.save(battle);
        }else{
            log = "You loose";
            battle.setStatus(BattleStatusEnum.FINISHED);
            battleRepository.save(battle);
            pokemonService.restPokemon(pokemon);
        }


        logService.saveBattle(validateBattle.trainer().getUuid(),log,validateBattle.battle().getUuid());
        return log;
    }




    private BattleActionValidateDTO validate(BattleActionDTO dto) throws Exception {
        Trainer trainer = trainerService.findById(dto.trainerId());
        Pokemon wildPokemon = pokemonService.findById(dto.wildPokemonId());
        Battle battle = battleRepository.findByuuid(dto.battleUUID());
        if (battle.getStatus().equals(BattleStatusEnum.FINISHED)){
            throw new Exception("The battle was finished");
        }
        Pokemon allyPokemon = trainer.getPokemons().get(0);
        Move move = moveService.findMoveByName(allyPokemon.getMoves().get(dto.moveIndex()).getMoveName());
        BattleActionValidateDTO battleAction = new BattleActionValidateDTO(
                trainer,
                wildPokemon,
                move,
                battle
        );

        return battleAction;
    }


}
