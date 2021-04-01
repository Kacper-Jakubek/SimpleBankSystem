package pl.jakubek.banksystem.entity;

import javax.persistence.*;

@Entity(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String login;
    private String password;
    private String email;
    @OneToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private PersonEntity personalData;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

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

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public PersonEntity getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonEntity personalData) {
        this.personalData = personalData;
    }

    public AccountEntity getAccount() { return account; }

    public void setAccount(AccountEntity account) { this.account = account; }
}
