package com.examples.usertaskmanagementapi.config;

import com.examples.usertaskmanagementapi.service.SecurityDataSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedSecurityData(
            SecurityDataSeeder securityDataSeeder
    ) {
        return args -> {
            securityDataSeeder.seed();
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}