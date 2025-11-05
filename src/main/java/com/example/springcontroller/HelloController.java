package com.example.springcontroller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Marks this class as a controller for REST APIs
public class HelloController {

    @GetMapping("/hello") // Maps HTTP GET requests to the /hello path
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}
