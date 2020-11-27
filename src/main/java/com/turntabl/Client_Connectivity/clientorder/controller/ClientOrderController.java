package com.turntabl.Client_Connectivity.clientorder.controller;

import com.turntabl.Client_Connectivity.auth.repository.UserRepository;
import com.turntabl.Client_Connectivity.clientorder.dao.ClientOrderDao;
import com.turntabl.Client_Connectivity.clientorder.model.*;
import com.turntabl.Client_Connectivity.exchangeorder.dao.ExchangeOrderDao;
import com.turntabl.Client_Connectivity.exchangeorder.model.ClientOrderData;
import com.turntabl.Client_Connectivity.exchangeorder.model.Data;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.exchangeorder.model.ResponseData;
import com.turntabl.Client_Connectivity.portfolio.doa.PortfolioDao;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import com.turntabl.Client_Connectivity.portfolio.model.PortfolioListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClientOrderController {

    @Autowired
    private final ClientOrderDao order;

    @Autowired
    private ExchangeOrderDao exchangeOrderDao;

    @Autowired
    private final PortfolioDao portfolioDao;

    @Autowired
    private UserRepository userRepository;

    public ClientOrderController(ClientOrderDao order, PortfolioDao portfolioDao) {
        this.order = order;
        this.portfolioDao = portfolioDao;
    }

    @GetMapping("/api/orders")
    List<ClientOrderData> getAllOrders(){
        return order.findAll().stream().map(
                order -> {

                    ClientOrderData clientOrderData = new ClientOrderData();
                    clientOrderData.setStatus(order.getStatus().toString());
                    clientOrderData.setState(order.getState().toString());
                    clientOrderData.setSide(order.getSide().toString());
                    clientOrderData.setQuantity(order.getQuantity());
                    clientOrderData.setProduct(order.getProduct());
                    clientOrderData.setPrice(order.getPrice());
                    clientOrderData.setClientOrderId(order.getClientOrderId());
                    clientOrderData.setAlgorithm(order.getAlgorithm());

                    return clientOrderData;
                }).collect(Collectors.toList());
    }

    @GetMapping("/api/orders/{id}")
    List<ClientOrderData> getOrder(@PathVariable Integer id){
        return order.findAllByClientOrderId(id).stream().map(
                order -> {

                    ClientOrderData clientOrderData = new ClientOrderData();
                    clientOrderData.setStatus(order.getStatus().toString());
                    clientOrderData.setState(order.getState().toString());
                    clientOrderData.setSide(order.getSide().toString());
                    clientOrderData.setQuantity(order.getQuantity());
                    clientOrderData.setProduct(order.getProduct());
                    clientOrderData.setPrice(order.getPrice());
                    clientOrderData.setClientOrderId(order.getClientOrderId());
                    clientOrderData.setAlgorithm(order.getAlgorithm());

                    return clientOrderData;
                }).collect(Collectors.toList());
    }

    //return a list of all the orders by a user.
    @GetMapping("/api/orders/user/{user_id}")
    List<ClientOrderData> getOrdersByUserId(@PathVariable Long user_id){
       return order.findAllByUserId(user_id)
               .stream().map(
                       order -> {

                           ClientOrderData clientOrderData = new ClientOrderData();
                           clientOrderData.setStatus(order.getStatus().toString());
                           clientOrderData.setState(order.getState().toString());
                           clientOrderData.setSide(order.getSide().toString());
                           clientOrderData.setQuantity(order.getQuantity());
                           clientOrderData.setProduct(order.getProduct());
                           clientOrderData.setPrice(order.getPrice());
                           clientOrderData.setClientOrderId(order.getClientOrderId());
                           clientOrderData.setAlgorithm(order.getAlgorithm());

                           return clientOrderData;
                       }).collect(Collectors.toList());
    }

//    @GetMapping("/api/orders/portfolio/{id}")
//    ClientOrder getOrderByPortfolioId(@PathVariable Integer portfolio_id){
//        return order.findAllByClientOrderId(portfolio_id);
//    }

    @PostMapping("/api/orders")
    ResponseData createOrder(@RequestBody SendOrderRequest orders){

        ResponseData responseData = new ResponseData();
        Data data = new Data();
        ClientOrderData clientOrderData = new ClientOrderData();

        ClientOrder clientOrder;
        SendOrderResponse sendOrderResponse = new SendOrderResponse();

        WebClient webClient = WebClient.create("https://seaborne-soap-consumer.herokuapp.com");

        String order_status = webClient.post()
                .uri("/api/soap_consumer/sendOrder")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(orders))
                .retrieve()
                .bodyToMono(SendOrderResponse.class)
                .block().getMessage();

        switch(order_status){
            case("valid"):

                ClientOrder clientOrder1 = new ClientOrder();

                ExchangeOrder exchangeOrder = new ExchangeOrder();

                Portfolio portfolio = portfolioDao.findByPortfolioId(orders.getPortfolio_Id());

                clientOrder1.setStatus(Status.OPEN);
                clientOrder1.setState(State.PENDING);
                clientOrder1.setSide(orders.getSide());
                clientOrder1.setQuantity(orders.getQuantity());
                clientOrder1.setProduct(orders.getProduct());
                clientOrder1.setAlgorithm(orders.getAlgorithm());
                clientOrder1.setPrice(orders.getPrice());


                clientOrder1.assignToPortfolio(portfolio);


                clientOrder = order.save(clientOrder1);
                exchangeOrder.setOrder(clientOrder);

                exchangeOrderDao.save(exchangeOrder);

                clientOrderData.setStatus(clientOrder.getStatus().toString());
                clientOrderData.setState(clientOrder.getState().toString());
                clientOrderData.setSide(clientOrder.getSide().toString());
                clientOrderData.setQuantity(clientOrder.getQuantity());
                clientOrderData.setProduct(clientOrder.getProduct());
                clientOrderData.setPrice(clientOrder.getPrice());
                clientOrderData.setClientOrderId(clientOrder.getClientOrderId());
                clientOrderData.setAlgorithm(clientOrder.getAlgorithm());

                data.setClientOrder(clientOrderData);
                data.setUserId(portfolio.getUser().getUserId());
                data.setPortfolioId(portfolio.getPortfolioId());

                responseData.setData(data);
                responseData.setMessage("Order sent");
                responseData.setStatus_code(HttpStatus.CREATED.value());

                break;
            case("invalid"):
                responseData.setMessage("Invalid Order");
                responseData.setStatus_code(HttpStatus.FORBIDDEN.value());
                break;
        }

        return responseData;
    }

    @PutMapping("/api/orders/{id}")
    ClientOrder updateClientOrder (@PathVariable Integer id, @RequestBody ClientOrder newClientOrder){
        ClientOrder clientOrder = order.findByClientOrderId(id);

        clientOrder.setAlgorithm(newClientOrder.getAlgorithm());
        clientOrder.setPortfolio(newClientOrder.getPortfolio());
        clientOrder.setPrice(newClientOrder.getPrice());
        clientOrder.setQuantity(newClientOrder.getQuantity());
        clientOrder.setProduct(newClientOrder.getProduct());
        clientOrder.setSide(newClientOrder.getSide());
        clientOrder.setStatus(newClientOrder.getStatus());

        return order.save(clientOrder);
    }
}
