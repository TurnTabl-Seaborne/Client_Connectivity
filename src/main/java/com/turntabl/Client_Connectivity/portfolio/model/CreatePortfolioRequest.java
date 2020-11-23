package com.turntabl.Client_Connectivity.portfolio.model;

public class CreatePortfolioRequest {
    private Long user_id;
    private double initial_amount;
    private Product product;


    public CreatePortfolioRequest() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public double getInitial_amount() {
        return initial_amount;
    }

    public void setInitial_amount(double initial_amount) {
        this.initial_amount = initial_amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
