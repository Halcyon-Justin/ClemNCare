package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO
        // LocalDate to Child DTO, how to?
        // Verify if address or guardian exists, it assigns the ID to other objects
        // Look into how to utilize Enums and LocalDateTime for objects in DTO
        // Add logging
        // Add Response Entities
        // Add Error Handling

    }
}

