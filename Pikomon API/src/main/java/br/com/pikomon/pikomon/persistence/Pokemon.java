package br.com.pikomon.pikomon.persistence;

import br.com.pikomon.pikomon.enums.NatureEnum;
import br.com.pikomon.pikomon.enums.TypeEnum;
import jakarta.persistence.*;


import java.util.*;


@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    private Integer number;

    private String trainerUUID;

    private String originalTrainer;

    private String actualTrainer;

    private Integer level;

    private String name;

    private String displayName;

    private TypeEnum type1;

    private TypeEnum type2;

    private Integer nextLevel;

    private Integer exp;

    private Integer baseExp;

    private NatureEnum nature;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status hp;
    @ManyToOne(cascade = CascadeType.ALL)
    private Status attack;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status defense;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status spAttack;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status spDefense;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status speed;

    private Boolean isShiny;

    private Integer deleted;

    private Date deletedDate;

    private Date createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PokemonMove> moves;

    public Pokemon() {
        this.moves = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTrainerUUID() {
        return trainerUUID;
    }

    public void setTrainerUUID(String trainerUUID) {
        this.trainerUUID = trainerUUID;
    }

    public String getOriginalTrainer() {
        return originalTrainer;
    }

    public void setOriginalTrainer(String originalTrainer) {
        this.originalTrainer = originalTrainer;
    }

    public String getActualTrainer() {
        return actualTrainer;
    }

    public void setActualTrainer(String actualTrainer) {
        this.actualTrainer = actualTrainer;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public TypeEnum getType1() {
        return type1;
    }

    public void setType1(TypeEnum type1) {
        this.type1 = type1;
    }

    public TypeEnum getType2() {
        return type2;
    }

    public void setType2(TypeEnum type2) {
        this.type2 = type2;
    }

    public Integer getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(Integer nextLevel) {
        this.nextLevel = nextLevel;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public Integer getBaseExp() {
        return baseExp;
    }

    public void setBaseExp(Integer baseExp) {
        this.baseExp = baseExp;
    }

    public NatureEnum getNature() {
        return nature;
    }

    public void setNature(NatureEnum nature) {
        this.nature = nature;
    }

    public Status getHp() {
        return hp;
    }

    public void setHp(Status hp) {
        this.hp = hp;
    }

    public Status getAttack() {
        return attack;
    }

    public void setAttack(Status attack) {
        this.attack = attack;
    }

    public Status getDefense() {
        return defense;
    }

    public void setDefense(Status defense) {
        this.defense = defense;
    }

    public Status getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(Status spAttack) {
        this.spAttack = spAttack;
    }

    public Status getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(Status spDefense) {
        this.spDefense = spDefense;
    }

    public Status getSpeed() {
        return speed;
    }

    public void setSpeed(Status speed) {
        this.speed = speed;
    }

    public Boolean getShiny() {
        return isShiny;
    }

    public void setShiny(Boolean shiny) {
        isShiny = shiny;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
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

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<PokemonMove> getMoves() {
        return moves;
    }

    public void setMoves(List<PokemonMove> moves) {
        this.moves = moves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(uuid, pokemon.uuid) && Objects.equals(number, pokemon.number) && Objects.equals(trainerUUID, pokemon.trainerUUID) && Objects.equals(originalTrainer, pokemon.originalTrainer) && Objects.equals(actualTrainer, pokemon.actualTrainer) && Objects.equals(level, pokemon.level) && Objects.equals(name, pokemon.name) && Objects.equals(displayName, pokemon.displayName) && type1 == pokemon.type1 && type2 == pokemon.type2 && Objects.equals(nextLevel, pokemon.nextLevel) && Objects.equals(exp, pokemon.exp) && Objects.equals(baseExp, pokemon.baseExp) && nature == pokemon.nature && Objects.equals(hp, pokemon.hp) && Objects.equals(attack, pokemon.attack) && Objects.equals(defense, pokemon.defense) && Objects.equals(spAttack, pokemon.spAttack) && Objects.equals(spDefense, pokemon.spDefense) && Objects.equals(speed, pokemon.speed) && Objects.equals(isShiny, pokemon.isShiny) && Objects.equals(deleted, pokemon.deleted) && Objects.equals(deletedDate, pokemon.deletedDate) && Objects.equals(createdDate, pokemon.createdDate) && Objects.equals(moves, pokemon.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, number, trainerUUID, originalTrainer, actualTrainer, level, name, displayName, type1, type2, nextLevel, exp, baseExp, nature, hp, attack, defense, spAttack, spDefense, speed, isShiny, deleted, deletedDate, createdDate, moves);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", number=" + number +
                ", trainerUUID='" + trainerUUID + '\'' +
                ", originalTrainer='" + originalTrainer + '\'' +
                ", actualTrainer='" + actualTrainer + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", type1=" + type1 +
                ", type2=" + type2 +
                ", nextLevel=" + nextLevel +
                ", exp=" + exp +
                ", baseExp=" + baseExp +
                ", nature=" + nature +
                ", hp=" + hp +
                ", attack=" + attack +
                ", defense=" + defense +
                ", spAttack=" + spAttack +
                ", spDefense=" + spDefense +
                ", speed=" + speed +
                ", isShiny=" + isShiny +
                ", deleted=" + deleted +
                ", deletedDate=" + deletedDate +
                ", createdDate=" + createdDate +
                ", moves=" + moves +
                '}';
    }
}
