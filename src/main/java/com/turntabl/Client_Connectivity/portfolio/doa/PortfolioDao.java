package com.turntabl.Client_Connectivity.portfolio.doa;

import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioDao extends JpaRepository<Portfolio, Integer> {
    Portfolio findAllById(Integer id);
}
