package br.com.pikomon.Pikomon.service;

import br.com.pikomon.Pikomon.enums.CategoryEnum;
import br.com.pikomon.Pikomon.enums.TypeEnum;
import br.com.pikomon.Pikomon.persistence.Move;
import br.com.pikomon.Pikomon.persistence.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public Integer calcDamage(Pokemon ally, Pokemon wild, Move move) {
        int damage = 0;

        Double modify = resistenceModify(move, wild);
        CategoryEnum category = move.getCategory();
        double stab = 1.0; //valor padrão, se o golpe for do mesmo tipo, o stab vai pra 1.5;
        Double crit = 1.0; // valor padrão, quando adicionar o critico, ele vai pra 1.5;
        if (move.getType().equals(ally.getType1()) || move.getType().equals(ally.getType2())) {
            stab = 1.5;
        }

        if (category.equals(CategoryEnum.PHYSICAL)) {
            damage = (int) Math.floor((((((double) 2 * ally.getLevel() / 5 + 2) * ally.getStatus().getAtak() * move.getPower() / wild.getStatus().getDef()) / 50) + 2) * stab * modify * crit * (80f / 100));
        } else if (category.equals(CategoryEnum.SPECIAL)) {
            damage = (int) Math.floor((((((double) 2 * ally.getLevel() / 5 + 2) * ally.getStatus().getSp_atk() * move.getPower() / wild.getStatus().getSp_def()) / 50) + 2) * stab * modify * crit * (80f / 100));
        }

        return damage;
    }

    private Double resistenceModify(Move move, Pokemon pokemon) {
        double modify = 1.0; // valor padrão, só será validado valores diferentes do golpe normal;
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
        boolean posion = pokemon.getType1().equals(TypeEnum.POISON);
        boolean ice = pokemon.getType1().equals(TypeEnum.ICE);
        boolean normal = pokemon.getType1().equals(TypeEnum.NORMAL);
        boolean electric = pokemon.getType1().equals(TypeEnum.ELECTRIC);

        if (move.getType().equals(TypeEnum.NORMAL)) {
            if (rock || steel) {
                return modify = 0.5;
            } else if (ghost) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.FIRE)) {
            if (fire || water
                    || rock || dragon) {
                return modify = 0.5;
            } else if (grass || ice
                    || bug || steel) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.WATER)) {
            if (water || grass
                    || dragon) {
                return modify = 0.5;
            } else if (fire || ground
                    || rock) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.ELECTRIC)) {
            if (electric || grass
                    || dragon) {
                return modify = 0.5;
            } else if (water || flying) {
                return modify = 2.0;
            } else if (ground) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.GRASS)) {
            if (fire || grass
                    || posion || flying
                    || bug || dragon
                    || steel) {
                return modify = 0.5;
            } else if (water || ground
                    || rock) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.ICE)) {
            if (fire || water
                    || ice || steel) {
                return modify = 0.5;
            } else if (grass || ground
                    || flying || dragon) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.FIGHTING)) {
            if (posion || flying
                    || psychic || bug
                    || fairy) {
                return modify = 0.5;
            } else if (grass || ice
                    || bug || steel) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.POISON)) {
            if (posion || ground
                    || rock || ghost) {
                return modify = 0.5;
            } else if (grass || fairy) {
                return modify = 2.0;
            } else if (steel) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.GROUND)) {
            if (bug || grass) {
                return modify = 0.5;
            } else if (fire || electric
                    || posion || rock || steel) {
                return modify = 2.0;
            } else if (flying) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.FLYING)) {
            if (electric || rock
                    || steel) {
                return modify = 0.5;
            } else if (grass || fighting || bug) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.PSYCHIC)) {
            if (psychic || steel) {
                return modify = 0.5;
            } else if (fighting || ground) {
                return modify = 2.0;
            } else if (dark) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.BUG)) {
            if (fire || fighting || posion || flying || ghost || steel || fairy) {
                return modify = 0.5;
            } else if (grass || psychic || dark) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.ROCK)) {
            if (fighting || ground || steel) {
                return modify = 0.5;
            } else if (fire || ice || flying || bug) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.GHOST)) {
            if (dark) {
                return modify = 0.5;
            } else if (psychic || ghost) {
                return modify = 2.0;
            } else if (normal) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.DRAGON)) {
            if (steel) {
                return modify = 0.5;
            } else if (dragon) {
                return modify = 2.0;
            } else if (fairy) {
                return modify = 0.0;
            }
        } else if (move.getType().equals(TypeEnum.STEEL)) {
            if (fire || water || electric || steel) {
                return modify = 0.5;
            } else if (ice || fairy) {
                return modify = 2.0;
            }
        } else if (move.getType().equals(TypeEnum.FAIRY)) {
            if (fire || posion || steel) {
                return modify = 0.5;
            } else if (dragon || fighting || dark) {
                return modify = 2.0;
            }
        }

        return modify;
    }

    public Pokemon calcExp(Pokemon pokemon,Pokemon pk) {
        int calc =(int) Math.floor((pk.getBaseExp() * pk.getLevel())/7); // calc medium fast type
        pokemon.setExp(pokemon.getExp()+calc);
        if (pokemon.getExp()>=pokemon.getNextLevel()){
            pokemon.setLevel(pokemon.getLevel()+1);
            calcNextLeveExp(pokemon);
        }
        return pokemon;
    }

    public Pokemon calcNextLeveExp(Pokemon pokemon){
        pokemon.setNextLevel(pokemon.getLevel()* pokemon.getLevel()* pokemon.getLevel());
        return pokemon;
    }
}
