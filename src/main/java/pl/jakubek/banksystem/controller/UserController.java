package pl.jakubek.banksystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.jakubek.banksystem.form.RegisterForm;
import pl.jakubek.banksystem.service.IUserService;

@RestController
@RequestMapping("/login")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String login(@RequestBody RegisterForm registerForm){
        return userService.findUser(registerForm);
    }

    @PutMapping()
    public String add(@RequestBody RegisterForm registerForm){
        LOG.info("added user {}", registerForm.getLogin());
        return userService.addUser(registerForm);
    }
}
