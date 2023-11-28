package halcyon.clemncare.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService jpaUserDetailsService;

    //Permissions for security. 
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) //We disable Cross Site Request Forgery (CSRF) because we are not using cookies
                .authorizeRequests(auth -> auth
                        .anyRequest().authenticated())    //Authorize any request that is authenticated
                .userDetailsService(jpaUserDetailsService) //Map Authenticated Users to a Security User with jpaUserDetailsService
                .httpBasic(Customizer.withDefaults()) //Basic Login function
                .build();
    
    }
    
    //Password Encoder so no one can see the password in DB
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
  
}
