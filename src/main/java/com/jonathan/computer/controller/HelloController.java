package com.jonathan.computer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloController {

    @Value("${app.message}") private String message;

    @GetMapping
    public String hello() {
        System.out.println(message);
        return "Hello world, from open bootcamp";
    }
}
