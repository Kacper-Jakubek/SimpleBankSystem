package pl.jakubek.banksystem.entity;

import javax.persistence.*;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity personalData;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public PersonEntity getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonEntity personalData) {
        this.personalData = personalData;
    }
}
