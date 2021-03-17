package pl.jakubek.banksystem.entity;

import javax.persistence.*;

@Entity(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @OneToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private AccountEntity account;

}
