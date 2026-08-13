package com.legacy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class LegacyLiftApplication {

    public static void main(String[] args) {
        SpringApplication.run(LegacyLiftApplication.class, args);
    }

    @GetMapping("/api/legacyCodebase")
    public String getLegacyCodebase() {
        return "Legacy Codebase";
    }
}
