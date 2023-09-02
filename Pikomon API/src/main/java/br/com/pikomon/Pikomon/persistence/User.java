package br.com.pikomon.Pikomon.persistence;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany
    private List<Trainer> trainers;

    private String name;

    private String login;

    private String password;

    private Boolean isDeleted;

    private Date createdDate;

    private Date deletedDate;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", trainers=" + trainers +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", isDeleted=" + isDeleted +
                ", createdDate=" + createdDate +
                ", deletedDate=" + deletedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(trainers, user.trainers) && Objects.equals(name, user.name) && Objects.equals(login, user.login) && Objects.equals(password, user.password) && Objects.equals(isDeleted, user.isDeleted) && Objects.equals(createdDate, user.createdDate) && Objects.equals(deletedDate, user.deletedDate);
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDeletedDate() {
        return deletedDate;
    }

    public void setDeletedDate(Date deletedDate) {
        this.deletedDate = deletedDate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, trainers, name, login, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
