package br.com.pikomon.Pikomon.persistence;


import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "ability")
public class Ability {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;

    private String effect;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEffect() {
        return effect;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public Ability(String name, String effect) {
        this.name = name;
        this.effect = effect;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ability(Integer id, String name, String effect) {
        this.id = id;
        this.name = name;
        this.effect = effect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ability ability = (Ability) o;
        return Objects.equals(id, ability.id) && Objects.equals(name, ability.name) && Objects.equals(effect, ability.effect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, effect);
    }

    @Override
    public String toString() {
        return "Ability{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", effect='" + effect + '\'' +
                '}';
    }

    public Ability() {
    }
}
