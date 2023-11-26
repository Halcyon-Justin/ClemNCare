package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO
        // Recursive Calling -- Need to sort Guardian and Child objects
        // Add logging
        // Add Response Entities
        // Add Error Handling
        // Add Pagination for Report Cards

    }
}

