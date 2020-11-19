package com.turntabl.Client_Connectivity.clientorder.controller;

import com.turntabl.Client_Connectivity.clientorder.dao.ClientOrderDao;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import com.turntabl.Client_Connectivity.clientorder.model.SendOrderResponse;
import com.turntabl.Client_Connectivity.clientorder.service.WebClientService;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

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
    SendOrderResponse createOrder(@RequestBody ClientOrder orders){

        ClientOrder clientOrder;
        SendOrderResponse sendOrderResponse = new SendOrderResponse();

        WebClient webClient = WebClient.create("http://localhost:8081");

        String order_status = webClient.post()
                .uri("/api/soap_consumer/sendOrder")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(orders))
                .retrieve()
                .bodyToMono(SendOrderResponse.class)
                .block().getMessage();

        switch(order_status){
            case("valid"):
                clientOrder = order.save(orders);
                sendOrderResponse.setData(clientOrder);
                sendOrderResponse.setStatusCode(HttpStatus.CREATED.value());
                sendOrderResponse.setMessage("Order Sent");
                break;
            case("invalid"):
                sendOrderResponse.setStatusCode(HttpStatus.FORBIDDEN.value());
                sendOrderResponse.setMessage("Invalid Order");
                break;
        }

        return sendOrderResponse;
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
