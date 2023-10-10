package br.com.pikomon.Pikomon.modal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Abilities {

    private AbilittyData ability;

    public AbilittyData getAbility() {
        return ability;
    }

    public void setAbility(AbilittyData ability) {
        this.ability = ability;
    }
}
