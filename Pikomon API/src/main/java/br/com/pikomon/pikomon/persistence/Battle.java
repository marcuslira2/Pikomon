package br.com.pikomon.pikomon.persistence;

import br.com.pikomon.pikomon.enums.BattleResultEnum;
import br.com.pikomon.pikomon.enums.BattleStatusEnum;
import br.com.pikomon.pikomon.enums.LocationEnum;
import br.com.pikomon.pikomon.enums.OpponentTypeEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "battle")
public class Battle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;
    private Integer userId;
    private Integer trainerId;
    private Long enemyId;
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTrainerId() {
        return trainerId;
    }

    public void setTrainerId(Integer trainerId) {
        this.trainerId = trainerId;
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Long getEnemyId() {
        return enemyId;
    }

    public void setEnemyId(Long enemyId) {
        this.enemyId = enemyId;
    }
}
