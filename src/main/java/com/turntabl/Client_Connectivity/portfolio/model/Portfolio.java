package com.turntabl.Client_Connectivity.portfolio.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
    private List<Stock> stocks;


//    @OneToMany( cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StockRecord> stocks = new ArrayList<>();


    public Portfolio(User user, double initial_amount, double revenue, double amount_spent, Stock stock) {
        this.user = user;
        this.initial_amount = initial_amount;
        this.revenue = revenue;
        this.amount_spent = amount_spent;
        this.stocks.add(stock);
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
            st = 1 + (int) (Math.random() * 9);
            this.stocks.add(Stock.values()[st]);
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

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
    public void addStock(Stock stock){
        this.stocks.add(stock);
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "id=" + id +
                ", user=" + user +
                ", initial_amount=" + initial_amount +
                ", revenue=" + revenue +
                ", amount_spent=" + amount_spent +
                ", stocks=" + stocks +
                '}';
    }
}
