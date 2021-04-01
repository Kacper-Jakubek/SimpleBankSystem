package pl.jakubek.banksystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.jakubek.banksystem.entity.UserEntity;
import pl.jakubek.banksystem.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
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
        UserEntity validUser = new UserEntity();
        validUser.setLogin("abc");
        validUser.setPassword("cba");

        userRepository.save(validUser);

        boolean correct = userService.findUser("abc", "cba");

        assertTrue(correct);

    }

    @Test
    void shouldAddUser(){
        userService.addUser("abcdef","Abcdef123","Abcdef123");

        boolean correct = userService.findUser("abcdef","Abcdef123");

        assertTrue(correct);
    }

    @Test
    void shouldValidateProperly(){
        String badLogin = userService.validateRegistration("abc","Abcdef123","Abcdef123");
        String badRepeat = userService.validateRegistration("abcdef","Abcdef123","Abcdef");
        String badPassword = userService.validateRegistration("abcdef","abcdef1","abcdef1");
        String correct = userService.validateRegistration("abcdef","Abcdef123","Abcdef123");

        assertEquals(badLogin, "login-failed");
        assertEquals(badRepeat, "repeatedPassword-failed");
        assertEquals(badPassword, "password-failed");
        assertEquals(correct, "validated");
    }
}