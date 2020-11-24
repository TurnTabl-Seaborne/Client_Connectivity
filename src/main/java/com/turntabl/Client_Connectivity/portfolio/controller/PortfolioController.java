package com.turntabl.Client_Connectivity.portfolio.controller;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import com.turntabl.Client_Connectivity.portfolio.doa.PortfolioDao;
import com.turntabl.Client_Connectivity.portfolio.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class PortfolioController {


    @Autowired
    private final PortfolioDao portfoliodao;


    @Autowired
    private final UserRepository userRepository;

    public PortfolioController(PortfolioDao portfoliodao, UserRepository userRepository) {
        this.portfoliodao = portfoliodao;
        this.userRepository = userRepository;
    }

    @GetMapping("/api/portfolio")
    List<PortfolioListResponse> getAllPortfolio(){
        return portfoliodao.findAll().stream().map(
                portfolio -> {

                    PortfolioListResponse portfolioListResponse = new PortfolioListResponse();
                    portfolioListResponse.setPortfolio_id(portfolio.getPortfolioId());
                    portfolioListResponse.setAmount_spent(portfolio.getAmount_spent());
                    portfolioListResponse.setInitial_amount(portfolio.getInitial_amount());
                    portfolioListResponse.setRevenue(portfolio.getRevenue());

                    return portfolioListResponse;
                }).collect(Collectors.toList());
    }

//    @GetMapping("/api/portfolio/{portfolio_id}")

    Integer getAllPortfolioById(@PathVariable Integer portfolio_id){

        Portfolio portfolio = portfoliodao.findByPortfolioId(portfolio_id);
//                 PortfolioResponse response = new PortfolioResponse();
//                 response.setPortfolio_id(portfolio.getPortfolioId());
//                 response.setUser_id(portfolio.getUser().getUserId());
//                 response.setInitial_amount(portfolio.getInitial_amount());
//                 response.setAmount_spent(portfolio.getAmount_spent());
//                 response.setRevenue(portfolio.getRevenue());
//
                 return portfolio.getPortfolioId();
//        return  portfolio_id;
    }

    @GetMapping("/api/portfolio/user/{user_id}")
    List<PortfolioListResponse> getAllPortfoliosByUserId(@PathVariable Long user_id){
        PortfolioResponse pResponse = new PortfolioResponse();
        return  portfoliodao.findAllByUserId(user_id).stream().map(
                portfolio -> {

                PortfolioListResponse portfolioListResponse = new PortfolioListResponse();
                    portfolioListResponse.setPortfolio_id(portfolio.getPortfolioId());
                    portfolioListResponse.setAmount_spent(portfolio.getAmount_spent());
                    portfolioListResponse.setInitial_amount(portfolio.getInitial_amount());
                    portfolioListResponse.setRevenue(portfolio.getRevenue());

                    return portfolioListResponse;
                }).collect(Collectors.toList());
    }



    @PostMapping("/api/portfolio/")
    PortfolioResponse addNewPortfolio(@RequestBody CreatePortfolioRequest createPortfolioRequest){
        PortfolioResponse portResponse = new PortfolioResponse();
        User user = new User();
        Optional<User> userOptional = userRepository.findById(createPortfolioRequest.getUser_id());

        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        

        Portfolio newPortfolio = new Portfolio();
        newPortfolio.setInitial_amount(createPortfolioRequest.getInitial_amount());
        newPortfolio.addProduct(createPortfolioRequest.getProduct());
        newPortfolio.assisgnToUser(user);

        newPortfolio = portfoliodao.save(newPortfolio);
        portResponse.setUser_id(user.getUserId());
        portResponse.setPortfolio_id(newPortfolio.getPortfolioId());
        portResponse.setInitial_amount(newPortfolio.getInitial_amount());
        portResponse.setAmount_spent(newPortfolio.getAmount_spent());
        portResponse.setRevenue(newPortfolio.getRevenue());

        return portResponse;
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
