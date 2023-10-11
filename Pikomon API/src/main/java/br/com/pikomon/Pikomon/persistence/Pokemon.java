package br.com.pikomon.Pikomon.persistence;

import jakarta.persistence.*;


import java.util.*;


@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uuid;

    private Integer id;

    private String originalTrainer;

    private String actualTrainer;

    private Integer level;

    private String name;

    private String displayName;

    private String type1;

    private String type2;

    private Integer nextLevel;

    private Integer exp;

    private Integer baseExp;

    private String effortType;

    private Integer effortValue;

    private String nature;

    private List<Integer> status;

    private List<Integer> base;

    private List<Integer> ev;

    private List<Integer> iv;

    private Boolean isShiny;

    private Integer deleted;

    private Date deletedDate;

    private Date createdDate;

    @ManyToMany
    private List<PokemonMove> moves;

    @ManyToMany
    private List<PokemonAbility> abilities;

    public Pokemon() {
        this.moves = new ArrayList<>();
        this.abilities = new ArrayList<>();
        this.status = new ArrayList<>(6);
        this.base = new ArrayList<>(6);
        this.ev = new ArrayList<>(6);
        this.iv = new ArrayList<>(6);

    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
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

    public String getEffortType() {
        return effortType;
    }

    public void setEffortType(String effortType) {
        this.effortType = effortType;
    }

    public Integer getEffortValue() {
        return effortValue;
    }

    public void setEffortValue(Integer effortValue) {
        this.effortValue = effortValue;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public List<Integer> getStatus() {
        return status;
    }

    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    public List<Integer> getBase() {
        return base;
    }

    public void setBase(List<Integer> base) {
        this.base = base;
    }

    public List<Integer> getEv() {
        return ev;
    }

    public void setEv(List<Integer> ev) {
        this.ev = ev;
    }

    public List<Integer> getIv() {
        return iv;
    }

    public void setIv(List<Integer> iv) {
        this.iv = iv;
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

    public List<PokemonAbility> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<PokemonAbility> abilities) {
        this.abilities = abilities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(uuid, pokemon.uuid) && Objects.equals(id, pokemon.id) && Objects.equals(originalTrainer, pokemon.originalTrainer) && Objects.equals(actualTrainer, pokemon.actualTrainer) && Objects.equals(level, pokemon.level) && Objects.equals(name, pokemon.name) && Objects.equals(displayName, pokemon.displayName) && Objects.equals(type1, pokemon.type1) && Objects.equals(type2, pokemon.type2) && Objects.equals(nextLevel, pokemon.nextLevel) && Objects.equals(exp, pokemon.exp) && Objects.equals(baseExp, pokemon.baseExp) && Objects.equals(effortType, pokemon.effortType) && Objects.equals(effortValue, pokemon.effortValue) && Objects.equals(nature, pokemon.nature) && Objects.equals(status, pokemon.status) && Objects.equals(base, pokemon.base) && Objects.equals(ev, pokemon.ev) && Objects.equals(iv, pokemon.iv) && Objects.equals(isShiny, pokemon.isShiny) && Objects.equals(deleted, pokemon.deleted) && Objects.equals(deletedDate, pokemon.deletedDate) && Objects.equals(createdDate, pokemon.createdDate) && Objects.equals(moves, pokemon.moves) && Objects.equals(abilities, pokemon.abilities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, id, originalTrainer, actualTrainer, level, name, displayName, type1, type2, nextLevel, exp, baseExp, effortType, effortValue, nature, status, base, ev, iv, isShiny, deleted, deletedDate, createdDate, moves, abilities);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "uuid=" + uuid +
                ", id=" + id +
                ", originalTrainer='" + originalTrainer + '\'' +
                ", actualTrainer='" + actualTrainer + '\'' +
                ", level=" + level +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", nextLevel=" + nextLevel +
                ", exp=" + exp +
                ", baseExp=" + baseExp +
                ", effortType='" + effortType + '\'' +
                ", effortValue=" + effortValue +
                ", nature='" + nature + '\'' +
                ", status=" + status +
                ", base=" + base +
                ", ev=" + ev +
                ", iv=" + iv +
                ", isShiny=" + isShiny +
                ", deleted=" + deleted +
                ", deletedDate=" + deletedDate +
                ", createdDate=" + createdDate +
                ", moves=" + moves +
                ", abilities=" + abilities +
                '}';
    }
}
