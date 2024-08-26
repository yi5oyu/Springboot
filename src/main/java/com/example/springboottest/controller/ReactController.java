package com.example.springboottest.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:9000")
public class ReactController {
    @GetMapping("/hi")
    public String test() {
        return "Hello, world!";
    }

    @PostMapping("/data")
    public String receiveData(String id, String pw) {
        System.out.println("Received data: " + id +"--"+ pw);

        return "Data received successfully!";
    }

}
