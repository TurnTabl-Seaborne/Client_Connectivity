package com.turntabl.Client_Connectivity.portfolio.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="portfolio")
public class Portfolio implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "portfolio_id")
    private Integer portfolioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "initial_amount")
    private double initial_amount;

    @Column(name = "revenue")
    private double revenue;

    @Column(name = "amount_spent")
    private double amount_spent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
    private List<ClientOrder> orders = new ArrayList<>();


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "portfolio")
    private List<StockRecord> stockRecords = new ArrayList<>();


    @ElementCollection(targetClass = Product.class)
    @JoinTable(name = "portfolio_products", joinColumns = @JoinColumn(name = "portfolio_portfolio_id"))
    @Column(name = "products", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Product> products = new ArrayList<>();



    public Portfolio(User user, double initial_amount, double revenue, double amount_spent, List<Product> products) {
        this.user = user;
        this.initial_amount = initial_amount;
        this.revenue = revenue;
        this.amount_spent = amount_spent;
        this.products = products;
    }


    public Portfolio(){
        this.initial_amount = 1000 + (int) (Math.random() * 5000);
        this.amount_spent = 0.0;
        this.revenue = (this.initial_amount - this.amount_spent);
        genStocks();
    }


    private void genStocks(){
        int st;
        for(int i =0; i < 5; i++){
            st = 1 + (int) (Math.random() * 7);
            this.products.add(Product.values()[st]);
        }
    }

    public Integer getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Integer portfolioId) {
        this.portfolioId = portfolioId;
    }

    public User getUser() {
        return user;
    }

    public List<StockRecord> getStockRecords() {
        return stockRecords;
    }

    public void setStockRecords(List<StockRecord> stockRecords) {
        this.stockRecords = stockRecords;
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void assisgnToUser(User user){
        this.user = user;
        this.user.addPortfolio(this);
    }

    public void addClientOrder(ClientOrder clientOrder){
        this.orders.add(clientOrder);
    }

    public void addProduct(Product product){
        this.products.add(product);
    }
}
