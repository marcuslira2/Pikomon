package br.com.pikomon.Pikomon.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "pokemon")
public class Pokemon {
    @Id
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

    private Integer hp;

    private Integer atk;

    private Integer def;

    private Integer spAtk;

    private Integer spDef;

    private Integer speed;

    private Integer baseHp;

    private Integer baseAtk;

    private Integer baseDef;

    private Integer baseSpAtk;

    private Integer baseSpDef;

    private Integer baseSpeed;

    private Integer ivHp;

    private Integer ivAtk;

    private Integer ivDef;

    private Integer ivSpAtk;

    private Integer ivSpDef;

    private Integer ivSpeed;

    private Integer evHp;

    private Integer evAtk;

    private Integer evDef;

    private Integer evSpAtk;

    private Integer evSpDef;

    private Integer evSpeed;

    @OneToMany
    private List<Move> moves;

    public Pokemon() {
        this.moves = new ArrayList<>();
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

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAtk() {
        return atk;
    }

    public void setAtk(Integer atk) {
        this.atk = atk;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(Integer spAtk) {
        this.spAtk = spAtk;
    }

    public Integer getSpDef() {
        return spDef;
    }

    public void setSpDef(Integer spDef) {
        this.spDef = spDef;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getBaseHp() {
        return baseHp;
    }

    public void setBaseHp(Integer baseHp) {
        this.baseHp = baseHp;
    }

    public Integer getBaseAtk() {
        return baseAtk;
    }

    public void setBaseAtk(Integer baseAtk) {
        this.baseAtk = baseAtk;
    }

    public Integer getBaseDef() {
        return baseDef;
    }

    public void setBaseDef(Integer baseDef) {
        this.baseDef = baseDef;
    }

    public Integer getBaseSpAtk() {
        return baseSpAtk;
    }

    public void setBaseSpAtk(Integer baseSpAtk) {
        this.baseSpAtk = baseSpAtk;
    }

    public Integer getBaseSpDef() {
        return baseSpDef;
    }

    public void setBaseSpDef(Integer baseSpDef) {
        this.baseSpDef = baseSpDef;
    }

    public Integer getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(Integer baseSpeed) {
        this.baseSpeed = baseSpeed;
    }

    public Integer getIvHp() {
        return ivHp;
    }

    public void setIvHp(Integer ivHp) {
        this.ivHp = ivHp;
    }

    public Integer getIvAtk() {
        return ivAtk;
    }

    public void setIvAtk(Integer ivAtk) {
        this.ivAtk = ivAtk;
    }

    public Integer getIvDef() {
        return ivDef;
    }

    public void setIvDef(Integer ivDef) {
        this.ivDef = ivDef;
    }

    public Integer getIvSpAtk() {
        return ivSpAtk;
    }

    public void setIvSpAtk(Integer ivSpAtk) {
        this.ivSpAtk = ivSpAtk;
    }

    public Integer getIvSpDef() {
        return ivSpDef;
    }

    public void setIvSpDef(Integer ivSpDef) {
        this.ivSpDef = ivSpDef;
    }

    public Integer getIvSpeed() {
        return ivSpeed;
    }

    public void setIvSpeed(Integer ivSpeed) {
        this.ivSpeed = ivSpeed;
    }

    public Integer getEvHp() {
        return evHp;
    }

    public void setEvHp(Integer evHp) {
        this.evHp = evHp;
    }

    public Integer getEvAtk() {
        return evAtk;
    }

    public void setEvAtk(Integer evAtk) {
        this.evAtk = evAtk;
    }

    public Integer getEvDef() {
        return evDef;
    }

    public void setEvDef(Integer evDef) {
        this.evDef = evDef;
    }

    public Integer getEvSpAtk() {
        return evSpAtk;
    }

    public void setEvSpAtk(Integer evSpAtk) {
        this.evSpAtk = evSpAtk;
    }

    public Integer getEvSpDef() {
        return evSpDef;
    }

    public void setEvSpDef(Integer evSpDef) {
        this.evSpDef = evSpDef;
    }

    public Integer getEvSpeed() {
        return evSpeed;
    }

    public void setEvSpeed(Integer evSpeed) {
        this.evSpeed = evSpeed;
    }

    public List<Move> getMoves() {
        return Collections.unmodifiableList(moves);
    }
    public void add (Move move){
        this.moves.add(move);
    }



    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
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
                ", hp=" + hp +
                ", atk=" + atk +
                ", def=" + def +
                ", spAtk=" + spAtk +
                ", spDef=" + spDef +
                ", speed=" + speed +
                ", baseHp=" + baseHp +
                ", baseAtk=" + baseAtk +
                ", baseDef=" + baseDef +
                ", baseSpAtk=" + baseSpAtk +
                ", baseSpDef=" + baseSpDef +
                ", baseSpeed=" + baseSpeed +
                ", ivHp=" + ivHp +
                ", ivAtk=" + ivAtk +
                ", ivDef=" + ivDef +
                ", ivSpAtk=" + ivSpAtk +
                ", ivSpDef=" + ivSpDef +
                ", ivSpeed=" + ivSpeed +
                ", evHp=" + evHp +
                ", evAtk=" + evAtk +
                ", evDef=" + evDef +
                ", evSpAtk=" + evSpAtk +
                ", evSpDef=" + evSpDef +
                ", evSpeed=" + evSpeed +
                ", moves=" + moves +
                '}';
    }
}
