package com.generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    private String printDefault()
    {
        return "Sample Generator Project in progress";
    }
}
