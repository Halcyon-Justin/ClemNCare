package halcyon.clemncare.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);

        //TODO:
        // Add various other calculations for Monthly earnings.
        // Fix report cards to hold true or false and cast nulls if no child Id can be found
        // Add additional testing for Billing
        // Add different/more verbose wording for errors -- NO NULL DATA OK STUFF
        // Add Swagger for API Documentation -- might work best when log in is done
        // Add Security
        // Add Authentication/Authorization
        // Add Logging
    }

}
