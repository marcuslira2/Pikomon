package br.com.pikomon.Pikomon.persistence;

import br.com.pikomon.Pikomon.enums.BattleResultEnum;
import br.com.pikomon.Pikomon.enums.BattleStatusEnum;
import br.com.pikomon.Pikomon.enums.LocationEnum;
import br.com.pikomon.Pikomon.enums.OpponentTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "battle")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer user_id;
    private Integer trainer_id;
    private Enum<OpponentTypeEnum> opponent;
    private Enum<LocationEnum> location;
    private Enum<BattleStatusEnum> status;
    private Enum<BattleResultEnum> result;

    public Battle() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Enum<BattleResultEnum> getResult() {
        return result;
    }

    public void setResult(Enum<BattleResultEnum> result) {
        this.result = result;
    }

    public Enum<BattleStatusEnum> getStatus() {
        return status;
    }

    public void setStatus(Enum<BattleStatusEnum> status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Integer trainer_id) {
        this.trainer_id = trainer_id;
    }

    public Enum<LocationEnum> getLocation() {
        return location;
    }

    public void setLocation(Enum<LocationEnum> location) {
        this.location = location;
    }

    public Enum<OpponentTypeEnum> getOpponent() {
        return opponent;
    }

    public void setOpponent(Enum<OpponentTypeEnum> opponent) {
        this.opponent = opponent;
    }
}