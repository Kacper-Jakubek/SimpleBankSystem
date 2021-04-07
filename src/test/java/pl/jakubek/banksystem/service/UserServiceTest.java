package pl.jakubek.banksystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubek.banksystem.entity.UserEntity;
import pl.jakubek.banksystem.form.RegisterForm;
import pl.jakubek.banksystem.repository.UserRepository;
import pl.jakubek.banksystem.service.validation.validationEnum;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @BeforeEach
    void clear(){ userRepository.deleteAll(); }

    @Test
    void shouldFindExistingUser(){
        RegisterForm registerForm = new RegisterForm();
        registerForm.setLogin("abc");
        registerForm.setPassword("cba");
        UserEntity validUser = new UserEntity();
        validUser.setLogin("abc");
        validUser.setPassword("cba");

        userRepository.save(validUser);

        String correct = userService.findUser(registerForm);

        assertEquals("valid",correct);

    }

    @Test
    void shouldAddUser(){
        RegisterForm registerForm = new RegisterForm();
        registerForm.setLogin("abcdef");
        registerForm.setPassword("Abcdef123");
        registerForm.setRepeated("Abcdef123");
        userService.addUser(registerForm);

        String correct = userService.findUser(registerForm);

        assertEquals("valid",correct);
    }

    @Test
    void shouldValidateProperly(){
        validationEnum badLogin = userService.validateRegistration("abc","Abcdef123","Abcdef123");
        validationEnum badRepeat = userService.validateRegistration("abcdef","Abcdef123","Abcdef");
        validationEnum badPassword = userService.validateRegistration("abcdef","abcdef1","abcdef1");
        validationEnum correct = userService.validateRegistration("abcdef","Abcdef123","Abcdef123");

        assertEquals(validationEnum.LOGIN, badLogin);
        assertEquals(validationEnum.REPEATED, badRepeat);
        assertEquals(validationEnum.PASSWORD, badPassword);
        assertEquals(validationEnum.VALIDATED, correct);
    }
}