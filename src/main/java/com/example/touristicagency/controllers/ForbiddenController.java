package com.example.touristicagency.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForbiddenController {

    @GetMapping("/forbidden")
    public String getForbiddenPage() {
        return "forbiddenPage";
    }
}