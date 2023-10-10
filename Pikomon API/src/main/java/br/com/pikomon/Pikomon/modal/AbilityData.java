package br.com.pikomon.Pikomon.modal;

import java.util.List;

public class AbilityData {

    private List<FlavorData> effect_entries;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FlavorData> getEffect_entries() {
        return effect_entries;
    }

    public void setEffect_entries(List<FlavorData> effect_entries) {
        this.effect_entries = effect_entries;
    }
}
