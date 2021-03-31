package pl.jakubek.banksystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jakubek.banksystem.entity.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Long> {
}
