package br.com.pikomon.pikomon.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemonmove")
public class PokemonMove {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String moveName;

    private String pokemonName;

    private Integer level;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMoveName() {
        return moveName;
    }

    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
