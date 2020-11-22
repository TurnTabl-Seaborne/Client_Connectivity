package com.turntabl.Client_Connectivity.portfolio.controller;

import com.turntabl.Client_Connectivity.portfolio.doa.PortfolioDao;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class PortfolioController {


    @Autowired
    private final PortfolioDao portfoliodao;

    public PortfolioController(PortfolioDao portfoliodao) {
        this.portfoliodao = portfoliodao;
    }

    @GetMapping("/api/portfolio")
    List<Portfolio> getAllPortfolio(){
        return portfoliodao.findAll();
    }

    @GetMapping("/api/portfolio/{id}")
    Portfolio getAllPortfolio(Integer id){
        return portfoliodao.findAllByPortfolioId(id);
    }

    @PostMapping("/api/portfolio/")
    Portfolio addNewPortfolio(@RequestBody Portfolio newPortfolio){
        return portfoliodao.save(newPortfolio);
    }

    @DeleteMapping("api/portfolio/{id}")
    void deletePortfolio(@PathVariable int id){
        portfoliodao.deleteById(id);
    }


    @PutMapping("/api/portfolio/{id}")
    Optional<Portfolio> updatePortfolio(@RequestBody Portfolio newPortfolio, @PathVariable int id){
        return portfoliodao.findById(id).map(
                portfolio -> {
                    portfolio.setInitial_amount(newPortfolio.getInitial_amount());
                    portfolio.setAmount_spent(newPortfolio.getAmount_spent());
                    portfolio.setRevenue(newPortfolio.getRevenue());
                    return portfoliodao.save(portfolio);
                }
        );
    }
}
