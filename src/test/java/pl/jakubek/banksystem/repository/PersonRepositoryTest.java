package pl.jakubek.banksystem.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubek.banksystem.entity.PersonEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void shouldSaveNewAccount() {
        PersonEntity personEntity = new PersonEntity();
        personEntity.setFirstName("Kacper");
        personEntity.setLastName("Jakubek");
        repository.save(personEntity);
        PersonEntity result = repository.findById(1L).orElse(null);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isPositive();
    }

}