package com.justsayit.beanstalk;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

    @GetMapping("/health2")
    public String checkHealth2() {
        return "healthy2";
    }
}
