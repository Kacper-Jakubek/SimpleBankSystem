package pl.jakubek.banksystem.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubek.banksystem.entity.AccountEntity;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @BeforeEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveNewAccount() {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(1);
        accountEntity.setAccountBalance(new BigDecimal(1000));

        AccountEntity result = repository.save(accountEntity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isPositive();
    }
}
