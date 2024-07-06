package br.com.pikomon.pikomon.enums;

import br.com.pikomon.pikomon.persistence.Pokemon;

public enum NatureEnum {
    ADAMANT("Adamant") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*1.1f));
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    NAUGHTY("Naughty") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*1.1f));
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    LONELY("Lonely") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*1.1f));
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    BRAVE("Brave") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*1.1f));
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    BOLD("Bold") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*1.1f));
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    IMPISH("Impish") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*1.1f));
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    LAX("Lax") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*1.1f));
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    RELAXED("Relaxed") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*1.1f));
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    MODEST("Modest") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*1.1f));
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    MILD("Mild") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*1.1f));
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    RASH("Rash") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*1.1f));
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    QUIET("Quiet") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*1.1f));
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    CALM("Calm") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*1.1f));
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    GENTLE("Gentle") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*1.1f));
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    CAREFUL("Careful") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*1.1f));
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    SASSY("Sassy") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*1.1f));
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    TIMID("Timid") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*1.1f));
            pokemon.getAttack().setBattleStatus((int) (pokemon.getAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    HASTY("Hasty") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*1.1f));
            pokemon.getDefense().setBattleStatus((int) (pokemon.getDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    JOLLY("Jolly") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*1.1f));
            pokemon.getSpAttack().setBattleStatus((int) (pokemon.getSpAttack().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    NAIVE("Naive") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            pokemon.getSpeed().setBattleStatus((int) (pokemon.getSpeed().getBattleStatus()*1.1f));
            pokemon.getSpDefense().setBattleStatus((int) (pokemon.getSpDefense().getBattleStatus()*0.9f));
            return pokemon;
        }
    },
    HARDY("Hardy") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            return pokemon;
        }
    },
    DOCILE("Bashful") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            return pokemon;
        }
    },
    QUIRKY("Quirky") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            return pokemon;
        }
    },
    SERIOUS("Serious") {
        @Override
        public Pokemon calcNature(Pokemon pokemon) {
            return pokemon;
        }
    };


    private final String name;

    NatureEnum(String name){
        this.name = name;
    }


    public abstract Pokemon calcNature(Pokemon pokemon);

    public String getName() {
        return name;
    }
}
