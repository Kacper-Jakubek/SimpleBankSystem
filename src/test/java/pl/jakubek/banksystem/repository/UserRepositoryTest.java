package pl.jakubek.banksystem.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubek.banksystem.entity.UserEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @BeforeEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveNewAccount() {
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin("abc");
        userEntity.setPassword("cba");

        UserEntity result = repository.save(userEntity);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isPositive();
    }

}