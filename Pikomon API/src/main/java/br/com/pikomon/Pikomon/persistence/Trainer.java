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

    private Boolean isDeleted;

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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return Objects.equals(id, trainer.id) && Objects.equals(name, trainer.name) && Objects.equals(money, trainer.money) && Objects.equals(isDeleted, trainer.isDeleted) && Objects.equals(deletedDate, trainer.deletedDate) && Objects.equals(createdDate, trainer.createdDate) && Objects.equals(pokemons, trainer.pokemons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, money, isDeleted, deletedDate, createdDate, pokemons);
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", isDeleted=" + isDeleted +
                ", deletedDate=" + deletedDate +
                ", createdDate=" + createdDate +
                ", pokemons=" + pokemons +
                '}';
    }
}


