package com.turntabl.Client_Connectivity.exchangeorder.controller;

import com.turntabl.Client_Connectivity.exchangeorder.dao.ExchangeOrderDao;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExchangeOrderController {

    @Autowired
    private final ExchangeOrderDao exchangeOrders;

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

    @PutMapping("/api/exchangeorders/{id}")
    ExchangeOrder updateExchangeOrder(@PathVariable Integer id, @RequestBody ExchangeOrder newExchangeOrder){
        ExchangeOrder exchangeOrder = exchangeOrders.findAllById(id);

        exchangeOrder.setOrderKey(newExchangeOrder.getOrderKey());
        exchangeOrder.setOrder(newExchangeOrder.getOrder());

        return exchangeOrders.save(exchangeOrder);
    }

}
