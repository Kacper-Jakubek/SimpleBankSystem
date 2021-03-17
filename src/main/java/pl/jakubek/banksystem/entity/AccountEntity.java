package pl.jakubek.banksystem.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "balance")
    private BigDecimal accountBalance;
}
