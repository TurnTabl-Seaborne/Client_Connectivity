package com.turntabl.Client_Connectivity.clientorder.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.exchangeorder.model.ExchangeOrder;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clientOrders")
public class ClientOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_order_id")
    private int clientOrderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(name = "product")
    private String product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "side")
    @Enumerated(EnumType.STRING)
    private Side side;

    @Column(name = "price")
    private double price;

    @Column(name = "algorithm")
    private String algorithm;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "order")
    private ExchangeOrder exchangeOrder;

    public ClientOrder(){}

    public ClientOrder(String product, int quantity, Side side, double price, String algorithm) {
        this.product = product;
        this.quantity = quantity;
        this.side = side;
        this.price = price;
        this.algorithm = algorithm;
    }

    public int getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(int clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Side getSide() {
        return side;
    }

    public ExchangeOrder getExchangeOrder() {
        return exchangeOrder;
    }

    public void setExchangeOrder(ExchangeOrder exchangeOrder) {
        this.exchangeOrder = exchangeOrder;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void assignToPortfolio(Portfolio portfolio){
        this.portfolio = portfolio;
        this.portfolio.addClientOrder(this);
    }

}
