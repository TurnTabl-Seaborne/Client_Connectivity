package com.turntabl.Client_Connectivity.portfolio.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="portfolio")
public class Portfolio {
    @Id @GeneratedValue
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    private double initial_amount;
    private double revenue;
    private double amount_spent;

    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientOrder> orders = new ArrayList<>();

    @ElementCollection(targetClass = Product.class)
    @Enumerated(EnumType.STRING)
    private List<Product> products = new ArrayList<>();


    public Portfolio(User user, double initial_amount, double revenue, double amount_spent, Product stock) {
        this.user = user;
        this.initial_amount = initial_amount;
        this.revenue = revenue;
        this.amount_spent = amount_spent;
        this.products.add(stock);
    }

    public Portfolio(User user, double initial_amount, double revenue, double amount_spent, ClientOrder orders, Product product) {
        this.user = user;
        this.initial_amount = initial_amount;
        this.revenue = revenue;
        this.amount_spent = amount_spent;
        this.orders.add(orders);
        this.products.add(product);
    }

    public Portfolio(){
        this.initial_amount = 1000 + (int) (Math.random() * 5000);
        this.amount_spent = 0.0;
        this.revenue = (this.initial_amount - this.amount_spent);
        genStocks();
    }


    private void genStocks(){
        int st;
        for(int i =0; i < 4; i++){
            st = 1 + (int) (Math.random() * 8);
            this.products.add(Product.values()[st]);
        }
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getInitial_amount() {
        return initial_amount;
    }

    public void setInitial_amount(double initial_amount) {
        this.initial_amount = initial_amount;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getAmount_spent() {
        return amount_spent;
    }

    public void setAmount_spent(double amount_spent) {
        this.amount_spent = amount_spent;
    }

    public List<ClientOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ClientOrder> orders) {
        this.orders = orders;
    }

    public void addOrder(ClientOrder order){
        this.orders.add(order);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", user=" + user +
                ", initial_amount=" + initial_amount +
                ", revenue=" + revenue +
                ", amount_spent=" + amount_spent +
                ", stocks=" + products +
                '}';
    }
}
