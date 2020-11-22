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

    @Query("select e from ExchangeOrder e where e.order.clientOrderId = :client_order_id")
    ExchangeOrder findByClientOrderId(@Param("client_order_id") int client_order_id);

    @Modifying(clearAutomatically = true)
    @Query("update ExchangeOrder e set e.orderKey = :order_key where e.order.clientOrderId = :client_order_id")
    int updatedExchangeOrderWithOrderKey(@Param("order_key") String order_key, @Param("client_order_id") int client_order_id);
    

}
