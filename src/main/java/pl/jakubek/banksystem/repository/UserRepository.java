package pl.jakubek.banksystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.jakubek.banksystem.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private final EntityManager entityManager;

    @Autowired
    public UserRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public UserEntity save(UserEntity userEntity){
        entityManager.persist(userEntity);
        return userEntity;
    }

    @Transactional
    public UserEntity update(UserEntity userEntity){
        entityManager.merge(userEntity);
        return userEntity;
    }

    @Transactional
    public Optional<UserEntity> getById(long id){
        return Optional.ofNullable(entityManager.find(UserEntity.class, id));
    }

    @Transactional
    public UserEntity getByLogin(String login){
        if(Optional.ofNullable(entityManager.createQuery("select u from user u where u.login = :login")
                .setParameter("login", login).getSingleResult()).isPresent()){
            return (UserEntity) entityManager.createQuery("select u from user u where u.login = :login")
                    .setParameter("login", login).getSingleResult();
        }else{
            return new UserEntity();
        }
    }

    @Transactional
    public List<UserEntity> findAll(){
        return entityManager.createQuery
                ("select u from user u", UserEntity.class).getResultList();
    }

    @Transactional
    public void delete(UserEntity userEntity){
        entityManager.remove
                (entityManager.contains(userEntity)?userEntity:entityManager.merge(userEntity));
    }

    @Transactional
    public void deleteById(long id){
        entityManager.createQuery("delete from user u where u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Transactional
    public void deleteAll(){
        entityManager.createQuery("delete from user u")
                .executeUpdate();
    }

    @Transactional
    public void truncate(){
        entityManager.createNativeQuery("truncate table user")
                .executeUpdate();
    }

}
