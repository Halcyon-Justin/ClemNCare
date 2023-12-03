package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO:
        // Add various other calculations for Monthly earnings.
        // Added additional testing for Billing
        // Add different/more verbose wording for errors -- NO NULL DATA OK STUFF
        // Add Swagger for API Documentation -- might work best when log in is done
        // Add Security
        // Add Authentication/Authorization
        // Add Logging
    }

}
