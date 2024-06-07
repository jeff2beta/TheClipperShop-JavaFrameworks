package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutPageController {
    // need a controller method to show page
   @GetMapping("/aboutForm")
    public String showAboutForm() {
        return "aboutForm";
    }

}
