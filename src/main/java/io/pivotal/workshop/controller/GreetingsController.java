package io.pivotal.workshop.controller;

import io.pivotal.workshop.config.PersonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingsController {

    @Autowired
    private PersonProperties personProperties;

    @Value("${message}")
    private String message;

    @GetMapping("/greeting")
    public String greeting() {
        String message = this.message + personProperties.getMessage();
        return message;
    }
}
