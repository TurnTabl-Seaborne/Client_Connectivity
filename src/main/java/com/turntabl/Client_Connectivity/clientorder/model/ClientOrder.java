package com.turntabl.Client_Connectivity.clientorder.model;

import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import javax.persistence.*;

@Entity
@Table(name = "clientOrders")
public class ClientOrder {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Portfolio portfolio;
    private String ticker;
    private int quantity;
    private Side side;
    private double price;
    private String algorithm;
    private State state;
    private Status status;

    public ClientOrder(Portfolio portfolio, String ticker, int quantity, Side side, double price, String algorithm, State state, Status status) {
        this.portfolio = portfolio;
        this.ticker = ticker;
        this.quantity = quantity;
        this.side = side;
        this.price = price;
        this.algorithm = algorithm;
        this.state = state;
        this.status = status;
    }

    public ClientOrder(){}

    public int getId() {
        return id;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
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
}
