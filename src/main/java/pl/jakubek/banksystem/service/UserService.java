package pl.jakubek.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubek.banksystem.entity.UserEntity;
import pl.jakubek.banksystem.repository.UserRepository;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean findUser(String login, String password){
        UserEntity userEntity = userRepository.getByLogin(login);
        return userEntity.getPassword().equals(password);
    }

    public void addUser(String login, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setLogin(login);
        userEntity.setPassword(password);
        userRepository.save(userEntity);
    }
}
