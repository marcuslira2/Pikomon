package br.com.pikomon.Pikomon.persistence;


import jakarta.persistence.*;

@Entity
@Table(name = "pokemonability")
public class PokemonAbility {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String abilityName;

    private String pokemonName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }
}
