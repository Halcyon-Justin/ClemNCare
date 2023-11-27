package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO:
        // Add Error Handling
        // Add logging
        // Add Security
        // Add Authentication/Authorization
        // Add Pagination for Report Cards

    }
}

