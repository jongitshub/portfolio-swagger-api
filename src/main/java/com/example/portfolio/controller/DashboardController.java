package com.example.portfolio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @GetMapping("/dashboard")
    public String getDashboard() {
         System.out.println("🎯 /api/dashboard endpoint hit");
        return "Welcome to your dashboard!";
    }
}
