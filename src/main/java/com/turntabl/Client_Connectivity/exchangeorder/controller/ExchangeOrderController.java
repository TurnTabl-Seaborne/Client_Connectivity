package com.turntabl.Client_Connectivity.exchangeorder.controller;

import com.turntabl.Client_Connectivity.clientorder.dao.ClientOrderDao;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import com.turntabl.Client_Connectivity.exchangeorder.dao.ExchangeOrderDao;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ExchangeOrderController {

    @Autowired
    private final ExchangeOrderDao exchangeOrders;

    @Autowired
    private ClientOrderDao clientOrderDao;

    public ExchangeOrderController(ExchangeOrderDao exchangeOrders) {
        this.exchangeOrders = exchangeOrders;
    }

    @GetMapping("/api/exchangeorders")
    List<ExchangeOrder> getAllExchangeOrders(){
        return exchangeOrders.findAll();
    }

    @GetMapping("/api/exchangeorders/{id}")
    ExchangeOrder getAllExchangeOrdersById(@PathVariable Integer id){
        return exchangeOrders.findAllById(id);
    }

    @PostMapping("/api/exchangeorders")
    ExchangeOrder createExchangeOrder(@RequestBody ExchangeOrder newExchangeOrder){
        return  exchangeOrders.save(newExchangeOrder);
    }

    @PostMapping("/api/exchangeorder")
    int updateExchangeOrder(@RequestBody ExchangeOrderResponse response){
       return exchangeOrders.updatedExchangeOrderWithOrderKey(response.getOrder_key(), response.getClient_order_id());
    }

    @PutMapping("/api/exchangeorders/{id}")
    ExchangeOrder updateExchangeOrder(@PathVariable Integer id, @RequestBody ExchangeOrder newExchangeOrder){
        ExchangeOrder exchangeOrder = exchangeOrders.findAllById(id);

        exchangeOrder.setOrderKey(newExchangeOrder.getOrderKey());
        exchangeOrder.setOrder(newExchangeOrder.getOrder());

        return exchangeOrders.save(exchangeOrder);
    }

}
