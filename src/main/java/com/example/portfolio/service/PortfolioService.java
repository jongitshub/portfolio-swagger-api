package com.example.portfolio.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.portfolio.model.PortfolioItem;
import com.example.portfolio.model.User;
import com.example.portfolio.repository.PortfolioItemRepository;

@Service
public class PortfolioService {

    private final PortfolioItemRepository portfolioItemRepository;

    public PortfolioService(PortfolioItemRepository portfolioItemRepository) {
        this.portfolioItemRepository = portfolioItemRepository;
    }

    public List<PortfolioItem> getUserPortfolioItems(User user) {
        return portfolioItemRepository.findByUser(user);
    }

    public PortfolioItem addPortfolioItem(PortfolioItem item) {
        return portfolioItemRepository.save(item);
    }

    public void deletePortfolioItem(Long id) {
        portfolioItemRepository.deleteById(id);
    }
}
