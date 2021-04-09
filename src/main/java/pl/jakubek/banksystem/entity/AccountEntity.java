package pl.jakubek.banksystem.entity;

import pl.jakubek.banksystem.form.TransactionForm;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "balance")
    private BigDecimal accountBalance;
    @Column(name = "number")
    private String accountNumber;
    @OneToMany(mappedBy = "from")
    List<TransactionEntity> transactionHistory;

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

    public String getAccountNumber() { return accountNumber; }

    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber; }

    public List<TransactionEntity> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionEntity> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
