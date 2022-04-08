package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController
{
    @GetMapping("")
    public String viewHomePage()
    {
        return "blank_page";
    }
}
