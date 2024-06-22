package br.com.pikomon.pikomon.persistence;

import jakarta.persistence.*;

@Entity
@Table(name = "pokemonstatus")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer hp;
    private Integer attack;
    private Integer def;
    private Integer sp_atk;
    private Integer sp_def;
    private Integer speed;

    public Status() {
        this.hp = 0;
        this.attack = 0;
        this.def = 0;
        this.sp_atk = 0;
        this.sp_def = 0;
        this.speed = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getAttack() {
        return attack;
    }

    public void setAttack(Integer attack) {
        this.attack = attack;
    }

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getSp_atk() {
        return sp_atk;
    }

    public void setSp_atk(Integer sp_atk) {
        this.sp_atk = sp_atk;
    }

    public Integer getSp_def() {
        return sp_def;
    }

    public void setSp_def(Integer sp_def) {
        this.sp_def = sp_def;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
}
