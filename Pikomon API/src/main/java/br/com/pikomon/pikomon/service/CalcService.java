package br.com.pikomon.pikomon.service;

import br.com.pikomon.pikomon.enums.CategoryEnum;
import br.com.pikomon.pikomon.persistence.Move;
import br.com.pikomon.pikomon.persistence.Pokemon;
import org.springframework.stereotype.Service;

@Service
public class CalcService {

    public Integer calcDamage(Pokemon ally, Pokemon wild, Move move) {
        int damage = 0;

        double modify = damageModify(move, wild);
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

    private Double damageModify(Move move, Pokemon pokemon) {
        return move.getType().resolveAttack(pokemon);
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
            pokemon.getHp().setEv(validateEv(enemyPokemon.getHp().getEffort(), pokemon.getHp().getEv()));
            pokemon.getAttack().setEv(validateEv(enemyPokemon.getAttack().getEffort(), pokemon.getAttack().getEv()));
            pokemon.getDefense().setEv(validateEv(enemyPokemon.getDefense().getEffort(), pokemon.getDefense().getEv()));
            pokemon.getSpAttack().setEv(validateEv(enemyPokemon.getSpAttack().getEffort(), pokemon.getSpAttack().getEv()));
            pokemon.getSpDefense().setEv(validateEv(enemyPokemon.getSpDefense().getEffort(), pokemon.getSpDefense().getEv()));
            pokemon.getSpeed().setEv(validateEv(enemyPokemon.getSpeed().getEffort(), pokemon.getSpeed().getEv()));
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
        return pokemon.getHp().getEv() + pokemon.getAttack().getEv() + pokemon.getDefense().getEv()
                + pokemon.getSpAttack().getEv() + pokemon.getSpDefense().getEv() + pokemon.getSpeed().getEv();
    }

}
