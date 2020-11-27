package com.turntabl.Client_Connectivity.exchangeorder.dao;

import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrderResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeOrderDao extends JpaRepository<ExchangeOrder,Integer> {
    ExchangeOrder findAllByExchangeOrderId(Integer id);

    @Query("select e from ExchangeOrder e where e.order.clientOrderId = ?1")
    ExchangeOrder findByClientOrderId(int client_order_id);


    @Modifying(clearAutomatically = true)
    @Query("update ExchangeOrder e set e.orderKey = ?1 where e.order.clientOrderId = ?2")
    int updatedExchangeOrderWithOrderKey(String order_key, int client_order_id);

}
