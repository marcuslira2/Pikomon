package br.com.pikomon.pikomon.enums;


import br.com.pikomon.pikomon.persistence.Pokemon;

public enum TypeEnum {

    FIRE("Fire") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.WATER)
                    || typeEnum.equals(TypeEnum.ROCK) || typeEnum.equals(TypeEnum.DRAGON)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.GRASS) || typeEnum.equals(TypeEnum.ICE)
                    || typeEnum.equals(TypeEnum.BUG) || typeEnum.equals(TypeEnum.STEEL)) {
                return 2.0;
            }
            return 1.0;        }
    },
    WATER("Water") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.WATER) || typeEnum.equals(TypeEnum.GRASS)
                    || typeEnum.equals(TypeEnum.DRAGON)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.GROUND)
                    || typeEnum.equals(TypeEnum.ROCK)) {
                return 2.0;
            }
            return 1.0;        }
    },
    GRASS("Grass") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.GRASS)
                    || typeEnum.equals(TypeEnum.POISON) || typeEnum.equals(TypeEnum.FLYING)
                    || typeEnum.equals(TypeEnum.BUG) || typeEnum.equals(TypeEnum.DRAGON)
                    || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.WATER) || typeEnum.equals(TypeEnum.GROUND)
                    || typeEnum.equals(TypeEnum.ROCK)) {
                return 2.0;
            }
            return 1.0;        }
    },
    FLYING("Flying") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.ELECTRIC) || typeEnum.equals(TypeEnum.ROCK)
                    || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.GRASS) || typeEnum.equals(TypeEnum.FIGHTING)
                    || typeEnum.equals(TypeEnum.BUG)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    ROCK("Rock") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIGHTING) || typeEnum.equals(TypeEnum.GROUND)
                    || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.ICE)
                    || typeEnum.equals(TypeEnum.FLYING) || typeEnum.equals(TypeEnum.BUG)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    GHOST("Ghost") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.DARK)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.PSYCHIC) || typeEnum.equals(TypeEnum.GHOST)) {
                return 2.0;
            } else if (typeEnum.equals(TypeEnum.NORMAL)) {
                return 0.0;
            }
            return 1.0;        }
    },
    GROUND("Ground") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.BUG) || typeEnum.equals(TypeEnum.GRASS)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.ELECTRIC)
                    || typeEnum.equals(TypeEnum.POISON) || typeEnum.equals(TypeEnum.ROCK)
                    || typeEnum.equals(TypeEnum.STEEL)) {
                return 2.0;
            } else if (typeEnum.equals(TypeEnum.FLYING)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    DRAGON("Dragon") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.DRAGON)) {
                return 2.0;
            } else if (typeEnum.equals(TypeEnum.FAIRY)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    PSYCHIC("Psychic") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.PSYCHIC) || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.FIGHTING) || typeEnum.equals(TypeEnum.GROUND)) {
                return 2.0;
            } else if (typeEnum.equals(TypeEnum.DARK)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    DARK("Dark") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIGHTING) || typeEnum.equals(TypeEnum.DARK) || typeEnum.equals(TypeEnum.FAIRY)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.PSYCHIC) || typeEnum.equals(TypeEnum.GHOST)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    FAIRY("Fairy") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.POISON)
                    || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.DRAGON) || typeEnum.equals(TypeEnum.FIGHTING)
                    || typeEnum.equals(TypeEnum.DARK)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    ICE("Ice") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.WATER)
                    || typeEnum.equals(TypeEnum.ICE) || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.GRASS) || typeEnum.equals(TypeEnum.GROUND)
                    || typeEnum.equals(TypeEnum.FLYING) || typeEnum.equals(TypeEnum.DRAGON)) {
                return 2.0;
            }
            return 1.0;        }
    },
    BUG("Bug") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.FIGHTING)
                    || typeEnum.equals(TypeEnum.POISON) || typeEnum.equals(TypeEnum.GHOST)
                    || typeEnum.equals(TypeEnum.STEEL) || typeEnum.equals(TypeEnum.FAIRY)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.GRASS) || typeEnum.equals(TypeEnum.PSYCHIC)
                    || typeEnum.equals(TypeEnum.DARK)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    STEEL("Steel") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.FIRE) || typeEnum.equals(TypeEnum.WATER)
                    || typeEnum.equals(TypeEnum.ELECTRIC) || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.ICE) || typeEnum.equals(TypeEnum.FAIRY)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    POISON("Poison") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.POISON) || typeEnum.equals(TypeEnum.GROUND)
                    || typeEnum.equals(TypeEnum.ROCK) || typeEnum.equals(TypeEnum.GHOST)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.GRASS) || typeEnum.equals(TypeEnum.FAIRY)) {
                return 2.0;
            } else if (typeEnum.equals(TypeEnum.STEEL)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    ELECTRIC("Electric") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.ELECTRIC) || typeEnum.equals(TypeEnum.GRASS)
                    || typeEnum.equals(TypeEnum.DRAGON)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.WATER) || typeEnum.equals(TypeEnum.FLYING)) {
                return 2.0;
            } else if (typeEnum.equals(TypeEnum.GROUND)) {
                return 0.0;
            }
            return 1.0;
        }
    },
    FIGHTING("Fighting") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.POISON) || typeEnum.equals(TypeEnum.FLYING)
                    || typeEnum.equals(TypeEnum.PSYCHIC) || typeEnum.equals(TypeEnum.BUG)
                    || typeEnum.equals(TypeEnum.FAIRY)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.NORMAL) || typeEnum.equals(TypeEnum.ROCK)
                    || typeEnum.equals(TypeEnum.ICE) || typeEnum.equals(TypeEnum.STEEL)
                    || typeEnum.equals(TypeEnum.DARK)) {
                return 2.0;
            }
            return 1.0;
        }
    },
    NORMAL("Normal") {
        @Override
        public Double resolveAttack(TypeEnum typeEnum) {
            if (typeEnum.equals(TypeEnum.ROCK) || typeEnum.equals(TypeEnum.STEEL)) {
                return 0.5;
            } else if (typeEnum.equals(TypeEnum.GHOST)) {
                return 0.0;
            }
            return 1.0;
        }
    };

    private final String name;

    TypeEnum(String name) {

        this.name = name;
    }

    public abstract Double resolveAttack(TypeEnum typeEnum);

    public String getName() {
        return name;
    }
}
