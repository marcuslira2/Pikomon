package br.com.pikomon.pikomon.enums;

public enum StatusEnum {
    HP("hp"),
    ATTACK("attack"),
    DEFENSE("defense"),
    SP_ATTACK("special-attack"),
    SP_DEFENSE("special-defense"),
    SPEED("speed"),
    ;

    private final String value;

    StatusEnum(String value) {
        this.value = value;
    }
}
