package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO
        // Create API methods that Save child with parent and vice-versa.
        //       -Create a 'Registration' API that will be used to:
        //              -Save a Child with a Parent and an Address.
        // Look into how to utilize Enums and LocalDateTime for objects in DTO

    }
}

