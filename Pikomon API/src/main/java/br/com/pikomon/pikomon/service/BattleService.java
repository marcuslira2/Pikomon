package br.com.pikomon.pikomon.service;

import br.com.pikomon.pikomon.dto.battle.BattleActionDTO;
import br.com.pikomon.pikomon.dto.battle.BattleActionValidateDTO;
import br.com.pikomon.pikomon.dto.battle.CreateBattleDTO;
import br.com.pikomon.pikomon.enums.BattleResultEnum;
import br.com.pikomon.pikomon.enums.BattleStatusEnum;
import br.com.pikomon.pikomon.enums.LocationEnum;
import br.com.pikomon.pikomon.enums.OpponentTypeEnum;
import br.com.pikomon.pikomon.persistence.Battle;
import br.com.pikomon.pikomon.persistence.Move;
import br.com.pikomon.pikomon.persistence.Pokemon;
import br.com.pikomon.pikomon.persistence.Trainer;
import br.com.pikomon.pikomon.repository.BattleRepository;
import br.com.pikomon.pikomon.service.pokemon.PokemonAttributesService;
import br.com.pikomon.pikomon.service.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;


@Service
public class BattleService {


    private final BattleRepository battleRepository;


    private final PokemonService pokemonService;


    private final TrainerService trainerService;


    private final LogService logService;


    private final MoveService moveService;


    private final CalcService calcService;


    private final PokemonAttributesService attributesService;

    private final Random rnd = new SecureRandom();

    public BattleService(BattleRepository battleRepository, PokemonService pokemonService, TrainerService trainerService, LogService logService, MoveService moveService, CalcService calcService, PokemonAttributesService attributesService) {
        this.battleRepository = battleRepository;
        this.pokemonService = pokemonService;
        this.trainerService = trainerService;
        this.logService = logService;
        this.moveService = moveService;
        this.calcService = calcService;
        this.attributesService = attributesService;
    }


    public Battle create(CreateBattleDTO dto) {
        Battle battle = new Battle();

        battle.setOpponent(OpponentTypeEnum.valueOf(dto.opponent()));
        battle.setLocation(LocationEnum.valueOf(dto.location()));
        Pokemon enemy = pokemonService.createWildPokemon(LocationEnum.valueOf(dto.location()));

        battle.setTrainerId(dto.trainer_id());
        battle.setUserId(dto.user_id());
        battle.setStatus(BattleStatusEnum.START);
        battle.setUuid(UUID.randomUUID().toString());
        battle.setEnemyId(enemy.getId());
        return battleRepository.save(battle);
    }

    public Battle findById(Long id) {
        return battleRepository.findById(id).orElseThrow(() -> new RuntimeException("Battle not found."));
    }

    public String makeAMove(BattleActionDTO dto) {
        BattleActionValidateDTO validateBattle = validate(dto);
        Battle battle = battleRepository.findByuuid(dto.battleUUID());
        Pokemon pokemon = validateBattle.trainer().getPokemons().get(0);
        Pokemon wildPokemon = validateBattle.wildPokemon();
        Move moveUsed = validateBattle.moveUsed();
        Move wildMoveUsed = moveService.findMoveByName(wildPokemon.getMoves().get(rnd.nextInt(3)).getMoveName());

        Integer damageAlly = calcService.calcDamage(pokemon, wildPokemon, moveUsed);
        Integer damageWild = calcService.calcDamage(wildPokemon, pokemon, wildMoveUsed);

        Integer newHpWild = wildPokemon.getHp().getBattleStatus() - damageAlly;
        Integer newHpAlly = pokemon.getHp().getBattleStatus() - damageWild;


        wildPokemon.getHp().setBattleStatus(newHpWild);
        if (wildPokemon.getHp().getBattleStatus() < 0) {
            wildPokemon.getHp().setBaseStatus(0);
        }
        pokemon.getHp().setBattleStatus(newHpAlly);
        if (pokemon.getHp().getBattleStatus() < 0) {
            pokemon.getHp().setBattleStatus(0);
        }

        String log;

        if (pokemon.getHp().getBattleStatus() > 0 && wildPokemon.getHp().getBattleStatus() > 0) {
            log = pokemon.getName() + " used " + pokemon.getMoves().get(0).getMoveName() + ".\n" +
                    "Deal " + damageAlly + " on " + wildPokemon.getName() + ". remains hp: " + wildPokemon.getHp().getBattleStatus() + "\n" +
                    "Enemy " + wildPokemon.getName() + " used " + wildMoveUsed.getName() + "\n" +
                    "Deal " + damageWild + " on " + pokemon.getName() + ". remains hp: " + pokemon.getHp().getBattleStatus();
            pokemonService.updatePokemon(pokemon);
            pokemonService.updatePokemon(wildPokemon);
        } else if (pokemon.getHp().getBattleStatus() > 0 || wildPokemon.getHp().getBattleStatus() == 0) {

            battle.setStatus(BattleStatusEnum.FINISHED);
            battle.setResult(BattleResultEnum.WIN);
            Integer nextLevel = pokemon.getNextLevel();
            pokemon = calcService.calcExp(pokemon, wildPokemon);
            log = "Battle has finished! you win";
            pokemon = attributesService.calcBattleStatus(pokemon);
            if (pokemon.getExp() > nextLevel) {
                log += "\n " + pokemon.getName() + " grew to LV." + pokemon.getLevel() + "!";
                log += "\n\n NEW STATUS: ";
                log += "\n MAX. HP: " + pokemon.getHp().getBattleStatus();
                log += "\n ATTACK: " + pokemon.getAttack().getBattleStatus();
                log += "\n DEFENSE: " + pokemon.getDefense().getBattleStatus();
                log += "\n SP. ATTACK: " + pokemon.getSpAttack().getBattleStatus();
                log += "\n SP. DEF: " + pokemon.getSpDefense().getBattleStatus();
                log += "\n SPEED: " + pokemon.getSpeed().getBattleStatus();
            }
            pokemonService.updatePokemon(pokemon);
            battleRepository.save(battle);
        } else {
            log = "You loose";
            battle.setStatus(BattleStatusEnum.FINISHED);
            battle.setResult(BattleResultEnum.LOSE);
            pokemon = attributesService.calcBattleStatus(pokemon);
            pokemonService.updatePokemon(pokemon);
            battleRepository.save(battle);
        }


        logService.saveBattle(validateBattle.trainer().getUuid(), log, validateBattle.battle().getUuid());
        return log;
    }


    private BattleActionValidateDTO validate(BattleActionDTO dto) {
        Trainer trainer = trainerService.findById(dto.trainerId());
        Pokemon wildPokemon = pokemonService.findById(dto.wildPokemonId());
        Battle battle = battleRepository.findByuuid(dto.battleUUID());
        if (battle.getStatus().equals(BattleStatusEnum.FINISHED)) {
            throw new IllegalArgumentException("The battle was finished");
        }
        Pokemon allyPokemon = trainer.getPokemons().get(0);
        Move move = moveService.findMoveByName(allyPokemon.getMoves().get(dto.moveIndex()).getMoveName());

        return new BattleActionValidateDTO(
                trainer,
                wildPokemon,
                move,
                battle
        );
    }


}
