package br.com.pikomon.pikomon.service;

import br.com.pikomon.pikomon.dto.battle.BattleActionDTO;
import br.com.pikomon.pikomon.dto.battle.BattleActionValidateDTO;
import br.com.pikomon.pikomon.dto.battle.CreateBattleDTO;
import br.com.pikomon.pikomon.enums.BattleStatusEnum;
import br.com.pikomon.pikomon.enums.LocationEnum;
import br.com.pikomon.pikomon.enums.OpponentTypeEnum;
import br.com.pikomon.pikomon.persistence.*;
import br.com.pikomon.pikomon.repository.BattleRepository;
import br.com.pikomon.pikomon.service.pokemon.PokemonAttributesService;
import br.com.pikomon.pikomon.service.pokemon.PokemonService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
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


    public Battle create(CreateBattleDTO dto) throws Exception {
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

    public String makeAMove(BattleActionDTO dto) throws Exception {
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
        if (wildPokemon.getStatus().getHp() < 0) {
            wildPokemon.getStatus().setHp(0);
        }
        pokemon.getStatus().setHp(newHpAlly);
        if (pokemon.getStatus().getHp() < 0) {
            pokemon.getStatus().setHp(0);
        }

        String log;

        if (pokemon.getStatus().getHp() > 0 && wildPokemon.getStatus().getHp() > 0) {
            log = pokemon.getName() + " used " + pokemon.getMoves().get(0).getMoveName() + ".\n" +
                    "Deal " + damageAlly + " on " + wildPokemon.getName() + ". remains hp: " + wildPokemon.getStatus().getHp() + "\n" +
                    "Enemy " + wildPokemon.getName() + " used " + wildMoveUsed.getName() + "\n" +
                    "Deal " + damageWild + " on " + pokemon.getName() + ". remains hp: " + pokemon.getStatus().getHp();
            pokemonService.updatePokemon(pokemon);
            pokemonService.updatePokemon(wildPokemon);
        } else if (pokemon.getStatus().getHp() > 0 || wildPokemon.getStatus().getHp() == 0) {

            battle.setStatus(BattleStatusEnum.FINISHED);
            Integer nextLevel = pokemon.getNextLevel();
            List<Integer> oldAttributes = attributesService.calcAttributes(pokemon);
            pokemon = calcService.calcExp(pokemon, wildPokemon);
            log = "Battle has finished! you win";
            List<Integer> attributes = attributesService.calcAttributes(pokemon);
            pokemon.settingStatus(attributes);
            pokemon.settingOriginStatus(attributes);
            if (pokemon.getExp() > nextLevel) {
                log += "\n " + pokemon.getName() + " grew to LV." + pokemon.getLevel() + "!";
                log += "\n MAX. HP: + " + (attributes.get(0) - oldAttributes.get(0));
                log += "\n ATTACK: + " + (attributes.get(1) - oldAttributes.get(1));
                log += "\n DEFENSE: + " + (attributes.get(2) - oldAttributes.get(2));
                log += "\n SP. ATTACK: + " + (attributes.get(3) - oldAttributes.get(3));
                log += "\n SP. DEF: + " + (attributes.get(4) - oldAttributes.get(4));
                log += "\n SPEED: + " + (attributes.get(5) - oldAttributes.get(5));
                log += "\n\n NEW STATUS: ";
                log += "\n MAX. HP: " + attributes.get(0);
                log += "\n ATTACK: " + attributes.get(1);
                log += "\n DEFENSE: " + attributes.get(2);
                log += "\n SP. ATTACK: " + attributes.get(3);
                log += "\n SP. DEF: " + attributes.get(4);
                log += "\n SPEED: " + attributes.get(5);
                pokemonService.updatePokemon(pokemon);
            }

            battleRepository.save(battle);
        } else {
            log = "You loose";
            battle.setStatus(BattleStatusEnum.FINISHED);
            battleRepository.save(battle);
            pokemonService.restPokemon(pokemon);
        }


        logService.saveBattle(validateBattle.trainer().getUuid(), log, validateBattle.battle().getUuid());
        return log;
    }


    private BattleActionValidateDTO validate(BattleActionDTO dto) throws Exception {
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
