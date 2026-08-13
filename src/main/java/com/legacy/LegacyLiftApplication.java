package com.legacy;

import com.legacy.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SecurityConfig.class)
public class LegacyLiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegacyLiftApplication.class, args);
    }
}
