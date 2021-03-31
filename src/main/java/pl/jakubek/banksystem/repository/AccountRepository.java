package pl.jakubek.banksystem.repository;

import org.springframework.data.repository.CrudRepository;
import pl.jakubek.banksystem.entity.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
}
