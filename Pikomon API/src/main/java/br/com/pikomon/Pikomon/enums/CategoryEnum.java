package br.com.pikomon.Pikomon.enums;

public enum CategoryEnum {


    PHYSICAL("Physical"),
    SPECIAL("Special"),
    STATUS("Status");

    private final String category;

    CategoryEnum(String category) {
        this.category = category;
    }
}
