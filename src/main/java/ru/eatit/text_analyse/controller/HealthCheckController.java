package ru.eatit.text_analyse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("healthcheck")
    public String ok() {
        return "ok";
    }
}
