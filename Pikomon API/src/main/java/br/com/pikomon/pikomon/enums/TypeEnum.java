package br.com.pikomon.pikomon.enums;


import br.com.pikomon.pikomon.persistence.Pokemon;

public enum TypeEnum {

    FIRE("Fire") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.WATER)
                    || pokemon.getType1().equals(TypeEnum.ROCK) || pokemon.getType1().equals(TypeEnum.DRAGON)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.ICE)
                    || pokemon.getType1().equals(TypeEnum.BUG) || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 2.0;
            }
            return 1.0;        }
    },
    WATER("Water") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.WATER) || pokemon.getType1().equals(TypeEnum.GRASS)
                    || pokemon.getType1().equals(TypeEnum.DRAGON)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.GROUND)
                    || pokemon.getType1().equals(TypeEnum.ROCK)) {
                return 2.0;
            }
            return 1.0;        }
    },
    GRASS("Grass") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.GRASS)
                    || pokemon.getType1().equals(TypeEnum.POISON) || pokemon.getType1().equals(TypeEnum.FLYING)
                    || pokemon.getType1().equals(TypeEnum.BUG) || pokemon.getType1().equals(TypeEnum.DRAGON)
                    || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.WATER) || pokemon.getType1().equals(TypeEnum.GROUND)
                    || pokemon.getType1().equals(TypeEnum.ROCK)) {
                return 2.0;
            }
            return 1.0;        }
    },
    FLYING("Flying") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.ELECTRIC) || pokemon.getType1().equals(TypeEnum.ROCK)
                    || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.FIGHTING)
                    || pokemon.getType1().equals(TypeEnum.BUG)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    ROCK("Rock") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIGHTING) || pokemon.getType1().equals(TypeEnum.GROUND)
                    || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.ICE)
                    || pokemon.getType1().equals(TypeEnum.FLYING) || pokemon.getType1().equals(TypeEnum.BUG)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    GHOST("Ghost") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.DARK)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.GHOST)) {
                return 2.0;
            } else if (pokemon.getType1().equals(TypeEnum.NORMAL)) {
                return 0.0;
            }
            return 1.0;        }
    },
    GROUND("Ground") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
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
    },
    DRAGON("Dragon") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.DRAGON)) {
                return 2.0;
            } else if (pokemon.getType1().equals(TypeEnum.FAIRY)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    PSYCHIC("Psychic") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.FIGHTING) || pokemon.getType1().equals(TypeEnum.GROUND)) {
                return 2.0;
            } else if (pokemon.getType1().equals(TypeEnum.DARK)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    DARK("Dark") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIGHTING) || pokemon.getType1().equals(TypeEnum.DARK) || pokemon.getType1().equals(TypeEnum.FAIRY)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.PSYCHIC) || pokemon.getType1().equals(TypeEnum.GHOST)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    FAIRY("Fairy") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.POISON)
                    || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.DRAGON) || pokemon.getType1().equals(TypeEnum.FIGHTING)
                    || pokemon.getType1().equals(TypeEnum.DARK)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    ICE("Ice") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.WATER)
                    || pokemon.getType1().equals(TypeEnum.ICE) || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.GRASS) || pokemon.getType1().equals(TypeEnum.GROUND)
                    || pokemon.getType1().equals(TypeEnum.FLYING) || pokemon.getType1().equals(TypeEnum.DRAGON)) {
                return 2.0;
            }
            return 1.0;        }
    },
    BUG("Bug") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
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
    },
    STEEL("Steel") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.FIRE) || pokemon.getType1().equals(TypeEnum.WATER)
                    || pokemon.getType1().equals(TypeEnum.ELECTRIC) || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.ICE) || pokemon.getType1().equals(TypeEnum.FAIRY)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    POISON("Poison") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
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
    },
    ELECTRIC("Electric") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
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
    },
    FIGHTING("Fighting") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
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
    },
    NORMAL("Normal") {
        @Override
        public Double resolveAttack(Pokemon pokemon) {
            if (pokemon.getType1().equals(TypeEnum.ROCK) || pokemon.getType1().equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (pokemon.getType1().equals(TypeEnum.GHOST)) {
                return 0.0;
            }
            return 1.0;
        }
    };

    private final String name;

    TypeEnum(String name) {

        this.name = name;
    }

    public abstract Double resolveAttack(Pokemon pokemon);

    public String getName() {
        return name;
    }
}
