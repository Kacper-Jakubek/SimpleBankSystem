package pl.jakubek.banksystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.jakubek.banksystem.repository.AccountRepository;

@SpringBootApplication
public class BanksystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(BanksystemApplication.class, args);
    }

}
