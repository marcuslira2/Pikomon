package br.com.pikomon.Pikomon.enums;

public enum CategoryEnum {


    PHYSICAL("Physical"),
    SPECIAL("Special"),
    EFFECT("Effect");

    private final String category;

    CategoryEnum(String category) {
        this.category = category;
    }
}
