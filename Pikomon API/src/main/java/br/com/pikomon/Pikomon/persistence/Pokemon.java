package br.com.pikomon.Pikomon.persistence;

import br.com.pikomon.Pikomon.enums.TypeEnum;
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

    private String effortType;

    private Integer effortValue;

    private String nature;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status status;
    @ManyToOne(cascade = CascadeType.ALL)
    private Status originStatus;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status base;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status ev;
    @ManyToOne(cascade=CascadeType.ALL)
    private Status iv;

    private Boolean isShiny;

    private Integer deleted;

    private Date deletedDate;

    private Date createdDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<PokemonMove> moves;

    public Pokemon() {
        this.moves = new ArrayList<>();
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getBase() {
        return base;
    }

    public void setBase(Status base) {
        this.base = base;
    }

    public Status getEv() {
        return ev;
    }

    public void setEv(Status ev) {
        this.ev = ev;
    }

    public Status getIv() {
        return iv;
    }

    public void setIv(Status iv) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return Objects.equals(id, pokemon.id) && Objects.equals(uuid, pokemon.uuid) && Objects.equals(number, pokemon.number) && Objects.equals(trainerUUID, pokemon.trainerUUID) && Objects.equals(originalTrainer, pokemon.originalTrainer) && Objects.equals(actualTrainer, pokemon.actualTrainer) && Objects.equals(level, pokemon.level) && Objects.equals(name, pokemon.name) && Objects.equals(displayName, pokemon.displayName) && Objects.equals(type1, pokemon.type1) && Objects.equals(type2, pokemon.type2) && Objects.equals(nextLevel, pokemon.nextLevel) && Objects.equals(exp, pokemon.exp) && Objects.equals(baseExp, pokemon.baseExp) && Objects.equals(effortType, pokemon.effortType) && Objects.equals(effortValue, pokemon.effortValue) && Objects.equals(nature, pokemon.nature) && Objects.equals(status, pokemon.status) && Objects.equals(base, pokemon.base) && Objects.equals(ev, pokemon.ev) && Objects.equals(iv, pokemon.iv) && Objects.equals(isShiny, pokemon.isShiny) && Objects.equals(deleted, pokemon.deleted) && Objects.equals(deletedDate, pokemon.deletedDate) && Objects.equals(createdDate, pokemon.createdDate) && Objects.equals(moves, pokemon.moves);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid, number, trainerUUID, originalTrainer, actualTrainer, level, name, displayName, type1, type2, nextLevel, exp, baseExp, effortType, effortValue, nature, status, base, ev, iv, isShiny, deleted, deletedDate, createdDate, moves);
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", uuid=" + uuid +
                ", number=" + number +
                ", trainerUUID=" + trainerUUID +
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
                '}';
    }

    public Status getOriginStatus() {
        return originStatus;
    }

    public void setOriginStatus(Status originStatus) {
        this.originStatus = originStatus;
    }


    public void settingStatus(List<Integer> list){
        this.getStatus().setHp(list.get(0));
        this.getStatus().setAttack(list.get(1));
        this.getStatus().setDef(list.get(2));
        this.getStatus().setSp_atk(list.get(3));
        this.getStatus().setSp_def(list.get(4));
        this.getStatus().setSpeed(list.get(5));
    }
    public void settingOriginStatus(List<Integer> list){
        this.getOriginStatus().setHp(list.get(0));
        this.getOriginStatus().setAttack(list.get(1));
        this.getOriginStatus().setDef(list.get(2));
        this.getOriginStatus().setSp_atk(list.get(3));
        this.getOriginStatus().setSp_def(list.get(4));
        this.getOriginStatus().setSpeed(list.get(5));
    }
}
