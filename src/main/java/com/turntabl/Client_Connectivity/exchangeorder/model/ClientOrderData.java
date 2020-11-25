package com.turntabl.Client_Connectivity.exchangeorder.model;

import com.turntabl.Client_Connectivity.clientorder.model.Side;
import com.turntabl.Client_Connectivity.clientorder.model.State;
import com.turntabl.Client_Connectivity.clientorder.model.Status;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import javax.persistence.*;

public class ClientOrderData {


    private int clientOrderId;

    private String product;

    private int quantity;


    private String side;


    private double price;

    private String algorithm;

    private String state;


    private String status;

    public ClientOrderData() {
    }

    public int getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(int clientOrderId) {
        this.clientOrderId = clientOrderId;
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

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
