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
    @Column(name = "number")
    private Long accountNumber;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Long getAccountNumber() { return accountNumber; }

    public void setAccountNumber(Long accountNumber) { this.accountNumber = accountNumber; }

}
