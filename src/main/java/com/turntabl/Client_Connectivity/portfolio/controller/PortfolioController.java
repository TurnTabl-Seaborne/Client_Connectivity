package com.turntabl.Client_Connectivity.portfolio.controller;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import com.turntabl.Client_Connectivity.portfolio.doa.PortfolioDao;
import com.turntabl.Client_Connectivity.portfolio.model.CreatePortfolioRequest;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.Port;
import java.util.List;
import java.util.Optional;
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
    List<Portfolio> getAllPortfolio(){
        return portfoliodao.findAll();
    }

    @GetMapping("/api/portfolio/{id}")
    Portfolio getAllPortfolio(Integer id){
        return portfoliodao.findAllByPortfolioId(id);
    }

    @PostMapping("/api/portfolio/")
    Portfolio addNewPortfolio(@RequestBody CreatePortfolioRequest createPortfolioRequest){

        User user = new User();
        Optional<User> userOptional = userRepository.findById(createPortfolioRequest.getUser_id());

        if(userOptional.isPresent()){
            user = userOptional.get();
        }
        

        Portfolio newPortfolio = new Portfolio();
        newPortfolio.setInitial_amount(createPortfolioRequest.getInitial_amount());
        newPortfolio.addProduct(createPortfolioRequest.getProduct());
        newPortfolio.assisgnToUser(user);


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
