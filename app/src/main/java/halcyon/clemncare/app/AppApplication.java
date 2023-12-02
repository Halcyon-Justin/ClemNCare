package halcyon.clemncare.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import halcyon.clemncare.app.model.User;
import halcyon.clemncare.app.repositories.UserRepository;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO:
        // Add Billing -- create system for potential payments and manage invoices for admin
        // Add Swagger for API Documentation -- might work best when log in is done
        // Add Security
        // Add Authentication/Authorization
        // Add Logging
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository users, PasswordEncoder encoder) {
        return args -> {
            users.save(new User("user", encoder.encode("password"), "ROLE_USER"));
            users.save(new User("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));
        };
    }

}
