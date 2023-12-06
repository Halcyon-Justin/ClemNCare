package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        //TODO:
        // Think of Due Date Timestamp creation and Automation of creation of invoices.
        //     -Testing for Service
        // Add Notification System
        // Add Custom Due Date Setting, Child Value for Payment Amount
        // Add Logging
        // Add Swagger for API Documentation
        // Add Security
        // Add Authentication/Authorization
        
    }

}
