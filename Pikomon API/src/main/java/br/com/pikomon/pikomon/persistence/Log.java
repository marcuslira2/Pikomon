package br.com.pikomon.pikomon.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userUuid;

    private String trainerUuid;

    private String description;

    private Date createDate;

    private String battleuuid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public String getTrainerUuid() {
        return trainerUuid;
    }

    public void setTrainerUuid(String trainerUuid) {
        this.trainerUuid = trainerUuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getBattleuuid() {
        return battleuuid;
    }

    public void setBattleuuid(String battleuuid) {
        this.battleuuid = battleuuid;
    }
}
