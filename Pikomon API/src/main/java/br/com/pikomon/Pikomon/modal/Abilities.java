package br.com.pikomon.Pikomon.modal;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Abilities {

    private AbiittiesData ability;

    public AbiittiesData getAbility() {
        return ability;
    }

    public void setAbility(AbiittiesData ability) {
        this.ability = ability;
    }
}
