package br.com.pikomon.Pikomon.enums;

import java.util.List;

public enum LocationEnum {
    PALLET_TOWN("Pallet Town",null ,null ),
    ROUTE_01("Route 1", new int[]{2, 5}, new int[]{2, 7}),
    VERIDIAN("Veridian City",null ,null ),
    ROUT_02("Route 2",new int[]{1, 2}, new int[]{1, 2});

    private final String location;
    private final int[] pokemonIdList;
    private final int[] pokemonLevels;

    LocationEnum(String location, int[] pokemonIdList, int[] pokemonLevels) {
        this.location = location;
        this.pokemonIdList = pokemonIdList;
        this.pokemonLevels = pokemonLevels;
    }

    public String getLocation() {
        return location;
    }

    public int[] getPokemonIdList() {
        return pokemonIdList;
    }

    public int[] getPokemonLevels() {
        return pokemonLevels;
    }
}
