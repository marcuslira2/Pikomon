package br.com.pikomon.pikomon.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonData {

    private Integer id;
    private String name;
    private Integer base_experience;
    private List<MoveData> moves;
    private List<StatData> stats;
    private List<TypeData> types;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Integer getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(Integer base_experience) {
        this.base_experience = base_experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MoveData> getMoves() {
        return Collections.unmodifiableList(moves);
    }

    public List<StatData> getStats() {
        return Collections.unmodifiableList(stats);
    }

    public List<TypeData> getTypes() {
        return Collections.unmodifiableList(types);
    }
    public void add (TypeData type){
        this.types.add(type);
    }
    public void add (MoveData move){
        this.moves.add(move);
    }
    public void add (StatData stats){
        this.stats.add(stats);
    }

    public PokemonData() {
        this.stats = new ArrayList<>();
        this.moves = new ArrayList<>();
        this.types = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "PokemonData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", moves=" + moves +
                ", stats=" + stats +
                ", types=" + types +
                '}';
    }
}
