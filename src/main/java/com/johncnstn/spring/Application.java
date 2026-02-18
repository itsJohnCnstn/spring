package com.johncnstn.spring;

import com.johncnstn.spring.jpa.PersonRepository;
import com.johncnstn.spring.jpa.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.NoSuchElementException;

@SpringBootApplication
public class Application {

    static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner runner(PersonRepository repository) {
        return args -> {
            User user = new User();
            user.setName("John");
            String mail = "john@email.com";
            user.setEmail(mail);
            repository.save(user);
            User saved = repository.findByEmail(mail).orElseThrow(NoSuchElementException::new);
            System.out.println(saved.getId() + ": " + saved.getName());
        };
    }

}
