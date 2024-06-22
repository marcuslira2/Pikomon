package br.com.pikomon.pikomon.enums;

public enum TypeEnum {

    FIRE("Fire"),
    WATER("Water"),
    GRASS("Grass"),
    FLYING("Flying"),
    ROCK("Rock"),
    GHOST("Ghost"),
    GROUND("Ground"),
    DRAGON("Dragon"),
    PSYCHIC("Psychic"),
    DARK("Dark"),
    FAIRY("Fairy"),
    ICE("Ice"),
    BUG("Bug"),
    STEEL("Steel"),
    POISON("Poison"),
    ELECTRIC("Electric"),
    FIGHTING("Fighting"),
    NORMAL("Normal");

    private final String tipo;

    TypeEnum(String tipo) {
        this.tipo = tipo;

    }
}
