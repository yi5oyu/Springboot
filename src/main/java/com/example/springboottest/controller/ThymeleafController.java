package com.example.springboottest.controller;

import static java.util.Arrays.asList;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThymeleafController {
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("text", "<h1>text</h1>");
        model.addAttribute("name", "lee");
        model.addAttribute("judge", "true");
        List<String> user = asList("aa","bb","cc");
        model.addAttribute("users", user);
        model.addAttribute("color", "blue");
        return "thymeleaf";
    }
}