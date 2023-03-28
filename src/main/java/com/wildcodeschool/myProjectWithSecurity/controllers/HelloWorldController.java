package com.wildcodeschool.myProjectWithSecurity.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @GetMapping("/")
    public String hello() {
        return "Welcome to the SHIELD";
    }
    @GetMapping("secret-bases")
    public String admin(){
        return "Hello admin";
    }
    @GetMapping("avengers/assemble")
    public String champion() {
        return "Avengers... Assemble";
    }
}
