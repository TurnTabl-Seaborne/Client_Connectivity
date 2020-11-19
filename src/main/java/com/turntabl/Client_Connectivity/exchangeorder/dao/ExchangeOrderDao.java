package com.turntabl.Client_Connectivity.exchangeorder.dao;

import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeOrderDao extends JpaRepository<ExchangeOrder,Integer> {
    ExchangeOrder findAllById(Integer id);
}
