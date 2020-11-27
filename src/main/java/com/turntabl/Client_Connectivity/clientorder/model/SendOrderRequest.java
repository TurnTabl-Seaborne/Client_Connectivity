package com.turntabl.Client_Connectivity.clientorder.model;

public class SendOrderRequest {
    private int portfolio_Id;
    private String product;
    private int quantity;
    private Side side;
    private double price;
    private String algorithm;

    public SendOrderRequest() {
    }

    public int getPortfolio_Id() {
        return portfolio_Id;
    }

    public void setPortfolio_Id(int portfolio_Id) {
        this.portfolio_Id = portfolio_Id;
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

}
