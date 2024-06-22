package br.com.pikomon.pikomon.service;

import br.com.pikomon.pikomon.enums.CategoryEnum;
import br.com.pikomon.pikomon.enums.StatusEnum;
import br.com.pikomon.pikomon.enums.TypeEnum;
import br.com.pikomon.pikomon.persistence.Move;
import br.com.pikomon.pikomon.persistence.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public Integer calcDamage(Pokemon ally, Pokemon wild, Move move) {
        int damage = 0;

        double modify = resistance(move, wild);
        CategoryEnum category = move.getCategory();
        double stab = 1.0;
        double critical = 1.0;
        if (move.getType().equals(ally.getType1()) || move.getType().equals(ally.getType2())) {
            stab = 1.5;
        }

        if (category.equals(CategoryEnum.PHYSICAL)) {
            damage = (int) Math.floor((((((double) 2 * ally.getLevel() / 5 + 2) * ally.getStatus().getAttack() * move.getPower() / wild.getStatus().getDef()) / 50) + 2) * stab * modify * critical * (80f / 100));
        } else if (category.equals(CategoryEnum.SPECIAL)) {
            damage = (int) Math.floor((((((double) 2 * ally.getLevel() / 5 + 2) * ally.getStatus().getSp_atk() * move.getPower() / wild.getStatus().getSp_def()) / 50) + 2) * stab * modify * critical * (80f / 100));
        }

        return damage;
    }

    private Double resistance(Move move, Pokemon pokemon) {
        double modify = 1.0;
        boolean fire = pokemon.getType1().equals(TypeEnum.FIRE);
        boolean water = pokemon.getType1().equals(TypeEnum.WATER);
        boolean grass = pokemon.getType1().equals(TypeEnum.GRASS);
        boolean dragon = pokemon.getType1().equals(TypeEnum.DRAGON);
        boolean fairy = pokemon.getType1().equals(TypeEnum.FAIRY);
        boolean flying = pokemon.getType1().equals(TypeEnum.FLYING);
        boolean fighting = pokemon.getType1().equals(TypeEnum.FIGHTING);
        boolean ghost = pokemon.getType1().equals(TypeEnum.GHOST);
        boolean psychic = pokemon.getType1().equals(TypeEnum.PSYCHIC);
        boolean dark = pokemon.getType1().equals(TypeEnum.DARK);
        boolean bug = pokemon.getType1().equals(TypeEnum.BUG);
        boolean steel = pokemon.getType1().equals(TypeEnum.STEEL);
        boolean ground = pokemon.getType1().equals(TypeEnum.GROUND);
        boolean rock = pokemon.getType1().equals(TypeEnum.ROCK);
        boolean poison = pokemon.getType1().equals(TypeEnum.POISON);
        boolean ice = pokemon.getType1().equals(TypeEnum.ICE);
        boolean normal = pokemon.getType1().equals(TypeEnum.NORMAL);
        boolean electric = pokemon.getType1().equals(TypeEnum.ELECTRIC);

        if (move.getType().equals(TypeEnum.NORMAL)) {
            if (rock || steel) {
                return 0.5;
            } else if (ghost) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.FIRE)) {
            if (fire || water
                    || rock || dragon) {
                return 0.5;
            } else if (grass || ice
                    || bug || steel) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.WATER)) {
            if (water || grass
                    || dragon) {
                return 0.5;
            } else if (fire || ground
                    || rock) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.ELECTRIC)) {
            if (electric || grass
                    || dragon) {
                return 0.5;
            } else if (water || flying) {
                return 2.0;
            } else if (ground) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.GRASS)) {
            if (fire || grass
                    || poison || flying
                    || bug || dragon
                    || steel) {
                return 0.5;
            } else if (water || ground
                    || rock) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.ICE)) {
            if (fire || water
                    || ice || steel) {
                return 0.5;
            } else if (grass || ground
                    || flying || dragon) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.FIGHTING)) {
            if (poison || flying
                    || psychic || bug
                    || fairy) {
                return 0.5;
            } else if (normal || rock
                    || ice || steel || dark) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.POISON)) {
            if (poison || ground
                    || rock || ghost) {
                return 0.5;
            } else if (grass || fairy) {
                return 2.0;
            } else if (steel) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.GROUND)) {
            if (bug || grass) {
                return 0.5;
            } else if (fire || electric
                    || poison || rock || steel) {
                return 2.0;
            } else if (flying) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.FLYING)) {
            if (electric || rock
                    || steel) {
                return 0.5;
            } else if (grass || fighting || bug) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.PSYCHIC)) {
            if (psychic || steel) {
                return 0.5;
            } else if (fighting || ground) {
                return 2.0;
            } else if (dark) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.BUG)) {
            if (fire || fighting || poison || flying || ghost || steel || fairy) {
                return 0.5;
            } else if (grass || psychic || dark) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.ROCK)) {
            if (fighting || ground || steel) {
                return 0.5;
            } else if (fire || ice || flying || bug) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.GHOST)) {
            if (dark) {
                return 0.5;
            } else if (psychic || ghost) {
                return 2.0;
            } else if (normal) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.DRAGON)) {
            if (steel) {
                return 0.5;
            } else if (dragon) {
                return 2.0;
            } else if (fairy) {
                return 0.0;
            }
        } else if (move.getType().equals(TypeEnum.STEEL)) {
            if (fire || water || electric || steel) {
                return 0.5;
            } else if (ice || fairy) {
                return 2.0;
            }
        } else if (move.getType().equals(TypeEnum.FAIRY)) {
            if (fire || poison || steel) {
                return 0.5;
            } else if (dragon || fighting || dark) {
                return 2.0;
            }
        }

        return modify;
    }

    public Pokemon calcExp(Pokemon pokemon,Pokemon pk) {
        int calc =(int) Math.floor((pk.getBaseExp() * (double)(pk.getLevel())/7)); // calc medium fast type
        pokemon.setExp(pokemon.getExp()+calc);
        pokemon = calcEv(pokemon,pk);
        if (pokemon.getExp()>=pokemon.getNextLevel()){
            pokemon.setLevel(pokemon.getLevel()+1);
            pokemon.setNextLevel(pokemon.getLevel()* pokemon.getLevel()* pokemon.getLevel());
        }
        return pokemon;
    }

    public Pokemon calcEv(Pokemon pokemon,Pokemon enemyPokemon){
        String effortType = enemyPokemon.getEffortType();
        Integer effortValue = enemyPokemon.getEffortValue();
        int totalEv = calculateTotalEv(pokemon);
        if (totalEv < 510){
            if (effortType.equalsIgnoreCase(StatusEnum.HP.name())){
                pokemon.getEv().setHp(validateEv(effortValue,pokemon.getEv().getHp()));
            }
            if (effortType.equalsIgnoreCase(StatusEnum.ATTACK.name())){
                pokemon.getEv().setAttack(validateEv(effortValue,pokemon.getEv().getAttack()));
            }
            if (effortType.equalsIgnoreCase(StatusEnum.DEFENSE.name())){
                pokemon.getEv().setDef(validateEv(effortValue,pokemon.getEv().getDef()));
            }
            if (effortType.equalsIgnoreCase(StatusEnum.SP_ATTACK.name())){
                pokemon.getEv().setSp_atk(validateEv(effortValue,pokemon.getEv().getSp_atk()));
            }
            if (effortType.equalsIgnoreCase(StatusEnum.SP_DEFENSE.name())){
                pokemon.getEv().setSp_def(validateEv(effortValue,pokemon.getEv().getSp_def()));
            }
            if (effortType.equalsIgnoreCase(StatusEnum.SPEED.name())){
                pokemon.getEv().setSpeed(validateEv(effortValue,pokemon.getEv().getSpeed()));
            }
        }
        return pokemon;
    }

    private Integer validateEv(Integer effort,Integer limit){
        if (limit < 255){
            limit+= effort;
            if (limit > 255){
                limit = 255;
            }
        }
        return limit;
    }

    private Integer calculateTotalEv(Pokemon pokemon){
        return pokemon.getEv().getHp()+pokemon.getEv().getAttack()+pokemon.getEv().getDef()+pokemon.getEv().getSp_atk()+pokemon.getEv().getSp_def()+pokemon.getEv().getSpeed();
    }
}
