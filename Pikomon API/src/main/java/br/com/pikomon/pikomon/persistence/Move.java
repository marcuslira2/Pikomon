package br.com.pikomon.pikomon.persistence;

import br.com.pikomon.pikomon.enums.CategoryEnum;
import br.com.pikomon.pikomon.enums.TypeEnum;
import jakarta.persistence.*;


@Entity
@Table(name = "move")
public class Move {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uuid;
    private Integer id;

    private String name;

    private Integer accuracy;

    private Integer power;

    private Integer pp;

    private Integer priority;

    private TypeEnum type;

    private CategoryEnum category;

    private String description;

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public TypeEnum getType() {
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Move{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accuracy=" + accuracy +
                ", power=" + power +
                ", pp=" + pp +
                ", priority=" + priority +
                ", type='" + type + '\'' +
                ", damage_class='" + category + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
