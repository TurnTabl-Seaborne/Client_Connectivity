package com.turntabl.Client_Connectivity.portfolio.doa;

import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import com.turntabl.Client_Connectivity.portfolio.model.PortfolioResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PortfolioDao extends JpaRepository<Portfolio, Integer> {

    @Query("select p from Portfolio p where p.user.UserId = ?1")
   List<Portfolio> findAllByUserId(Long user_id);
//
//    @Query("select p from Portfolio p where p.portfolioId= ?1 ")
//    Portfolio findByPortfolioId(Integer portfolio_id);

    Portfolio findByPortfolioId(Integer portfolioId);
}

