package br.com.pikomon.Pikomon.modal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MoveRequestData {
    private Integer id;
    private String name;
    private Integer accuracy;
    private Integer power;
    private Integer pp;
    private ClassDamageData damage_class;
    private TypesData type;
    private Integer priority;
    private List<DescriptionData> effect_entries;

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

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Integer getPp() {
        return pp;
    }

    public void setPp(Integer pp) {
        this.pp = pp;
    }

    public ClassDamageData getDamage_class() {
        return damage_class;
    }

    public void setDamage_class(ClassDamageData damage_class) {
        this.damage_class = damage_class;
    }

    public TypesData getType() {
        return type;
    }

    public void setType(TypesData type) {
        this.type = type;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public List<DescriptionData> getEffect_entries() {
        return effect_entries;
    }

    public void setEffect_entries(List<DescriptionData> effect_entries) {
        this.effect_entries = effect_entries;
    }
}
