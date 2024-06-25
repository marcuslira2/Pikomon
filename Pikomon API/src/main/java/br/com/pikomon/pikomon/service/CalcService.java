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
            damage = (int) Math.floor((((((double) 2 * ally.getLevel() / 5 + 2) * ally.getAttack().getBattleStatus()
                    * move.getPower() / wild.getDefense().getBattleStatus()) / 50) + 2) * stab * modify * critical * (80f / 100));
        } else if (category.equals(CategoryEnum.SPECIAL)) {
            damage = (int) Math.floor((((((double) 2 * ally.getLevel() / 5 + 2) * ally.getSpAttack().getBattleStatus()
                    * move.getPower() / wild.getSpDefense().getBattleStatus()) / 50) + 2) * stab * modify * critical * (80f / 100));
        }

        return damage;
    }

    private Double resistance(Move move, Pokemon pokemon) {
        if (move.getType().equals(TypeEnum.NORMAL)) {
            return resolveNormalAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.FIRE)) {
            return resolveFireAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.WATER)) {
            return resolveWaterAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.ELECTRIC)) {
            return resolveElectricAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.GRASS)) {
            return resolveGrassAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.ICE)) {
            return resolveIceAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.FIGHTING)) {
            return resolveFightingAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.POISON)) {
            return resolvePoisonAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.GROUND)) {
            return resolveGroundAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.FLYING)) {
            return resolveFlyingAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.PSYCHIC)) {
            return resolvePsychAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.BUG)) {
            return resolveBugAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.ROCK)) {
            return resolveRockAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.GHOST)) {
            return resolveGhostAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.DRAGON)) {
            return resolveDragonAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.STEEL)) {
            return resolveSteelAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.FAIRY)) {
            return resolveFairyAttack(pokemon);
        } else if (move.getType().equals(TypeEnum.DARK)) {
            return resolveDarkAttack(pokemon);
        }

        return 1.0;
    }

    public Pokemon calcExp(Pokemon pokemon, Pokemon pk) {
        int calc = (int) Math.floor((pk.getBaseExp() * (double) (pk.getLevel()) / 7)); // calc medium fast type
        pokemon.setExp(pokemon.getExp() + calc);
        pokemon = calcEv(pokemon, pk);
        if (pokemon.getExp() >= pokemon.getNextLevel()) {
            pokemon.setLevel(pokemon.getLevel() + 1);
            pokemon.setNextLevel(pokemon.getLevel() * pokemon.getLevel() * pokemon.getLevel());
        }
        return pokemon;
    }

    public Pokemon calcEv(Pokemon pokemon, Pokemon enemyPokemon) {
        int totalEv = calculateTotalEv(pokemon);
        if (totalEv < 510) {
            pokemon.getHp().setEV(validateEv(enemyPokemon.getHp().getEffort(), pokemon.getHp().getEV()));
            pokemon.getAttack().setEV(validateEv(enemyPokemon.getAttack().getEffort(), pokemon.getAttack().getEV()));
            pokemon.getDefense().setEV(validateEv(enemyPokemon.getDefense().getEffort(), pokemon.getDefense().getEV()));
            pokemon.getSpAttack().setEV(validateEv(enemyPokemon.getSpAttack().getEffort(), pokemon.getSpAttack().getEV()));
            pokemon.getSpDefense().setEV(validateEv(enemyPokemon.getSpDefense().getEffort(), pokemon.getSpDefense().getEV()));
            pokemon.getSpeed().setEV(validateEv(enemyPokemon.getSpeed().getEffort(), pokemon.getSpeed().getEV()));
        }
        return pokemon;
    }

    private Integer validateEv(Integer effort, Integer limit) {
        if (limit < 255) {
            limit += effort;
            if (limit > 255) {
                limit = 255;
            }
        }
        return limit;
    }

    private Integer calculateTotalEv(Pokemon pokemon) {
        return pokemon.getHp().getEV() + pokemon.getAttack().getEV() + pokemon.getDefense().getEV()
                + pokemon.getSpAttack().getEV() + pokemon.getSpDefense().getEV() + pokemon.getSpeed().getEV();
    }

    private Double resolveNormalAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.ROCK) || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.GHOST)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolveFireAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.WATER)
                || pokemon.getType1().equals(TypeEnum.ROCK) || pokemon.getType1().equals(TypeEnum.DRAGON)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.ICE)
                || pokemon.getType1().equals(TypeEnum.BUG) || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveWaterAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.WATER) || pokemon.getType1().equals(TypeEnum.GRASS)
                || pokemon.getType1().equals(TypeEnum.DRAGON)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.GROUND)
                || pokemon.getType1().equals(TypeEnum.ROCK)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveGrassAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.GRASS)
                || pokemon.getType1().equals(TypeEnum.POISON) || pokemon.getType1().equals(TypeEnum.FLYING)
                || pokemon.getType1().equals(TypeEnum.BUG) || pokemon.getType1().equals(TypeEnum.DRAGON)
                || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.WATER) || pokemon.getType1().equals(TypeEnum.GROUND)
                || pokemon.getType1().equals(TypeEnum.ROCK)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveIceAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.WATER)
                || pokemon.getType1().equals(TypeEnum.ICE) || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.GROUND)
                || pokemon.getType1().equals(TypeEnum.FLYING) || pokemon.getType1().equals(TypeEnum.DRAGON)) {
            return 2.0;
        }
        return 0.0;
    }

    private Double resolveGhostAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.DARK)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.GHOST)) {
            return 2.0;
        } else if (pokemon.getType1().equals(TypeEnum.NORMAL)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolvePsychAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.FIGHTING) || pokemon.getType1().equals(TypeEnum.GROUND)) {
            return 2.0;
        } else if (pokemon.getType1().equals(TypeEnum.DARK)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolveDarkAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIGHTING) || pokemon.getType1().equals(TypeEnum.DARK) || pokemon.getType1().equals(TypeEnum.FAIRY)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.GHOST)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveDragonAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.DRAGON)) {
            return 2.0;
        } else if (pokemon.getType1().equals(TypeEnum.FAIRY)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolveElectricAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.ELECTRIC) || pokemon.getType1().equals(TypeEnum.GRASS)
                || pokemon.getType1().equals(TypeEnum.DRAGON)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.WATER) || pokemon.getType1().equals(TypeEnum.FLYING)) {
            return 2.0;
        } else if (pokemon.getType1().equals(TypeEnum.GROUND)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolveFightingAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.POISON) || pokemon.getType1().equals(TypeEnum.FLYING)
                || pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.BUG)
                || pokemon.getType1().equals(TypeEnum.FAIRY)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.NORMAL) || pokemon.getType1().equals(TypeEnum.ROCK)
                || pokemon.getType1().equals(TypeEnum.ICE) || pokemon.getType1().equals(TypeEnum.STEEL)
                || pokemon.getType1().equals(TypeEnum.DARK)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolvePoisonAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.POISON) || pokemon.getType1().equals(TypeEnum.GROUND)
                || pokemon.getType1().equals(TypeEnum.ROCK) || pokemon.getType1().equals(TypeEnum.GHOST)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.FAIRY)) {
            return 2.0;
        } else if (pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolveGroundAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.BUG) || pokemon.getType1().equals(TypeEnum.GRASS)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.ELECTRIC)
                || pokemon.getType1().equals(TypeEnum.POISON) || pokemon.getType1().equals(TypeEnum.ROCK)
                || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 2.0;
        } else if (pokemon.getType1().equals(TypeEnum.FLYING)) {
            return 0.0;
        }
        return 1.0;
    }

    private Double resolveFlyingAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.ELECTRIC) || pokemon.getType1().equals(TypeEnum.ROCK)
                || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.FIGHTING)
                || pokemon.getType1().equals(TypeEnum.BUG)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveBugAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.FIGHTING)
                || pokemon.getType1().equals(TypeEnum.POISON) || pokemon.getType1().equals(TypeEnum.GHOST)
                || pokemon.getType1().equals(TypeEnum.STEEL) || pokemon.getType1().equals(TypeEnum.FAIRY)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.PSYCHIC)
                || pokemon.getType1().equals(TypeEnum.DARK)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveFairyAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.POISON)
                || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.DRAGON) || pokemon.getType1().equals(TypeEnum.FIGHTING)
                || pokemon.getType1().equals(TypeEnum.DARK)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveRockAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIGHTING) || pokemon.getType1().equals(TypeEnum.GROUND)
                || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.ICE)
                || pokemon.getType1().equals(TypeEnum.FLYING) || pokemon.getType1().equals(TypeEnum.BUG)) {
            return 2.0;
        }
        return 1.0;
    }

    private Double resolveSteelAttack(Pokemon pokemon) {
        if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.WATER)
                || pokemon.getType1().equals(TypeEnum.ELECTRIC) || pokemon.getType1().equals(TypeEnum.STEEL)) {
            return 0.5;
        } else if (pokemon.getType1().equals(TypeEnum.ICE) || pokemon.getType1().equals(TypeEnum.FAIRY)) {
            return 2.0;
        }
        return 1.0;
    }

}
