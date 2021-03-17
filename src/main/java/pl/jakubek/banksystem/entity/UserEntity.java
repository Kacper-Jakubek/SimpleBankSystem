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


}
