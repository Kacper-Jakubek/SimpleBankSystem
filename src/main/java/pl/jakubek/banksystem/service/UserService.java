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

    public String validateRegistration(String login, String password, String repeatedPassword){

        if(login.length()<4 || login.length()>20){
            return "login-failed";
        }

        if(!password.equals(repeatedPassword)){
            return "repeatedPassword-failed";
        }

        boolean containsOnlyLowercase=true;
        boolean containsOnlyLetters=true;

        for (char c:password.toCharArray()) {
            if(Character.isUpperCase(c)){
                containsOnlyLowercase=false;
            }
            if(Character.isDigit(c)){
                containsOnlyLetters=false;
            }
        }
        if(containsOnlyLowercase||containsOnlyLetters){
            return "password-failed";
        }
        return"validated";
    }

    public boolean findUser(String login, String password){
        UserEntity userEntity = userRepository.getByLogin(login);
        return userEntity.getPassword().equals(password);
    }

    public void addUser(String login, String password, String repeatedPassword){
        String validation = validateRegistration(login, password, repeatedPassword);
        if(validation.equals("validated")){
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(login);
            userEntity.setPassword(password);
            userRepository.save(userEntity);
        }
    }
}
