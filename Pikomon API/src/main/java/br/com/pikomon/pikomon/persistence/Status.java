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
    private Integer Iv;
    private Integer Ev;
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
        return Iv;
    }

    public void setIv(Integer iv) {
        Iv = iv;
    }

    public Integer getEv() {
        return Ev;
    }

    public void setEv(Integer ev) {
        this.Ev = ev;
    }

    public Integer getBattleStatus() {
        return battleStatus;
    }

    public void setBattleStatus(Integer battleStatus) {
        this.battleStatus = battleStatus;
    }
}
