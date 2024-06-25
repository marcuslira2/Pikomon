package br.com.pikomon.pikomon.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemonstatus")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer baseStatus;
    private Integer effort;
    private Integer iv;
    private Integer ev;
    private Integer battleStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaseStatus() {
        return baseStatus;
    }

    public void setBaseStatus(Integer baseStatus) {
        this.baseStatus = baseStatus;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Integer getIv() {
        return iv;
    }

    public void setIv(Integer iv) {
        this.iv = iv;
    }

    public Integer getEv() {
        return ev;
    }

    public void setEv(Integer ev) {
        this.ev = ev;
    }

    public Integer getBattleStatus() {
        return battleStatus;
    }

    public void setBattleStatus(Integer battleStatus) {
        this.battleStatus = battleStatus;
    }
}
