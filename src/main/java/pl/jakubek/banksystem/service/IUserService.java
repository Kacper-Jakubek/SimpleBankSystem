package pl.jakubek.banksystem.service;

import pl.jakubek.banksystem.form.RegisterForm;

public interface IUserService {
    String addUser(RegisterForm registerForm);
    String findUser(RegisterForm registerForm);
}
