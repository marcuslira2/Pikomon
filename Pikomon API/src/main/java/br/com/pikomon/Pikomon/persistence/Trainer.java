package br.com.pikomon.Pikomon.persistence;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private Integer money;

    private Integer deleted;

    private Date deletedDate;

    private Date createdDate;


    @OneToMany
    private List<Pokemon> pokemons = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }
    public void add(Pokemon pokemon){ this.pokemons.add(pokemon);}

    public void addAll(List<Pokemon> pokemons) { this.pokemons.addAll(pokemons);}

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }


    public Trainer() {
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreateDate(Date createDate) {
        this.createdDate = createDate;
    }

    public Trainer(Integer id, String name, Integer money, Integer deleted, Date deletedDate, Date createdDate, List<Pokemon> pokemons) {
        this.id = id;
        this.name = name;
        this.money = money;
        this.deleted = deleted;
        this.deletedDate = deletedDate;
        this.createdDate = createdDate;
        this.pokemons = pokemons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(id, trainer.id) && Objects.equals(name, trainer.name) && Objects.equals(money, trainer.money) && Objects.equals(deleted, trainer.deleted) && Objects.equals(deletedDate, trainer.deletedDate) && Objects.equals(createdDate, trainer.createdDate) && Objects.equals(pokemons, trainer.pokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, money, deleted, deletedDate, createdDate, pokemons);
    }
}


