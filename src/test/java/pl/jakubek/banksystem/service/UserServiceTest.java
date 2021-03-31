package pl.jakubek.banksystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import pl.jakubek.banksystem.entity.PersonEntity;
import pl.jakubek.banksystem.entity.UserEntity;
import pl.jakubek.banksystem.repository.UserRepository;

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
        userService.addUser("abd", "dba");

        boolean correct = userService.findUser("abd","dba");

        assertTrue(correct);
    }
}