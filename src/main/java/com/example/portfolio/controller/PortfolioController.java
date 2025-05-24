package com.example.portfolio.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.portfolio.model.PortfolioItem;
import com.example.portfolio.model.User;
import com.example.portfolio.repository.UserRepository;
import com.example.portfolio.service.PortfolioService;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioController {

    private final PortfolioService portfolioService;
    private final UserRepository userRepository;

    public PortfolioController(PortfolioService portfolioService, UserRepository userRepository) {
        this.portfolioService = portfolioService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<PortfolioItem>> getUserPortfolioItems(Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        List<PortfolioItem> items = portfolioService.getUserPortfolioItems(user);
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<PortfolioItem> addPortfolioItem(@RequestBody PortfolioItem item, Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));
        item.setUser(user);
        PortfolioItem savedItem = portfolioService.addPortfolioItem(item);
        return ResponseEntity.ok(savedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePortfolioItem(@PathVariable Long id) {
        portfolioService.deletePortfolioItem(id);
        return ResponseEntity.noContent().build();
    }
}
