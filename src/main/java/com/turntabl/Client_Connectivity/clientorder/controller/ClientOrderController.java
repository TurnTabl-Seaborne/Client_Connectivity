package com.turntabl.Client_Connectivity.clientorder.controller;

import com.turntabl.Client_Connectivity.clientorder.dao.ClientOrderDao;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientOrderController {

    @Autowired
    private final ClientOrderDao order;

    public ClientOrderController(ClientOrderDao order) {
        this.order = order;
    }

    @GetMapping("/api/orders")
    List<ClientOrder> getAllOrders(){
        return order.findAll();
    }

    @GetMapping("/api/orders/{id}")
    ClientOrder getOrder(@PathVariable Integer id){
        return order.findAllById(id);
    }
    @PostMapping("/api/orders")
    ClientOrder createOrder(@RequestBody ClientOrder orders){
        return  order.save(orders);
    }

    @PutMapping("/api/orders/{id}")
    ClientOrder updateClientOrder (@PathVariable Integer id, @RequestBody ClientOrder newClientOrder){
        ClientOrder clientOrder = order.findAllById(id);

        clientOrder.setAlgorithm(newClientOrder.getAlgorithm());
        clientOrder.setPortfolio(newClientOrder.getPortfolio());
        clientOrder.setPrice(newClientOrder.getPrice());
        clientOrder.setQuantity(newClientOrder.getQuantity());
        clientOrder.setTicker(newClientOrder.getTicker());
        clientOrder.setSide(newClientOrder.getSide());
        clientOrder.setStatus(newClientOrder.getStatus());

        return order.save(clientOrder);
    }
}
