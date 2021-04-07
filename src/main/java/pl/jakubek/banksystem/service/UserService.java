package pl.jakubek.banksystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.jakubek.banksystem.entity.AccountEntity;
import pl.jakubek.banksystem.entity.PersonEntity;
import pl.jakubek.banksystem.entity.UserEntity;
import pl.jakubek.banksystem.form.RegisterForm;
import pl.jakubek.banksystem.repository.AccountRepository;
import pl.jakubek.banksystem.repository.PersonRepository;
import pl.jakubek.banksystem.repository.UserRepository;
import pl.jakubek.banksystem.service.validation.validationEnum;

import java.math.BigDecimal;

@Service
public class UserService implements IUserService {

    UserRepository userRepository;
    AccountRepository accountRepository;
    PersonRepository personRepository;

    @Autowired
    public UserService(UserRepository userRepository, AccountRepository accountRepository, PersonRepository personRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.personRepository = personRepository;
    }

    public validationEnum validateRegistration(String login, String password, String repeatedPassword){

        if(login.length()<4 || login.length()>20){
            return validationEnum.LOGIN;
        }

        if(!password.equals(repeatedPassword)){
            return validationEnum.REPEATED;
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
            return validationEnum.PASSWORD;
        }
        return validationEnum.VALIDATED;
    }

    public String findUser(RegisterForm registerForm){
        UserEntity userEntity = userRepository.getByLogin(registerForm.getLogin());
        if(userEntity.getPassword().equals(registerForm.getPassword())){
            return "valid";
        }else {
            return "invalid";
        }
    }

    public String addUser(RegisterForm registerForm){
        switch (validateRegistration(registerForm.getLogin(), registerForm.getPassword(), registerForm.getRepeated())){
            case VALIDATED:
            UserEntity userEntity = new UserEntity();
            userEntity.setLogin(registerForm.getLogin());
            userEntity.setPassword(registerForm.getPassword());
            userEntity.setEmail(registerForm.getEmail());

            PersonEntity personEntity = new PersonEntity();
            personEntity.setFirstName(registerForm.getFirstName());
            personEntity.setLastName(registerForm.getLastName());
            personEntity.setCity(registerForm.getCity());
            personEntity.setHouseNumber(registerForm.getHouseNumber());
            personEntity.setStreet(registerForm.getStreet());
            personEntity.setPostalCode(registerForm.getPostalCode());
            personRepository.save(personEntity);

            AccountEntity accountEntity = new AccountEntity();
            accountEntity.setAccountBalance(new BigDecimal(0));
            accountEntity.setAccountNumber((long) (Math.random()*(1e27-1e25)+1e25));
            accountRepository.save(accountEntity);

            userEntity.setAccount(accountEntity);
            userEntity.setPersonalData(personEntity);
            userRepository.save(userEntity);
            return "validated";
            case LOGIN:
                return "login-failed";
            case PASSWORD:
                return "password-failed";
            case REPEATED:
                return "repeated-failed";
        }
        return "unknown error";
    }
}
