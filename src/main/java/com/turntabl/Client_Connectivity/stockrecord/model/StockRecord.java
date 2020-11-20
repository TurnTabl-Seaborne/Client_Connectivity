package com.turntabl.Client_Connectivity.stockrecord.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import javax.persistence.*;

@Entity
@Table(name = "stockRecord")
public class StockRecord {
    @Id
    @GeneratedValue
    private int id;
    private String ticker;
    private int quantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Portfolio portfolio;

    public StockRecord(String ticker, int quantity, User user) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.user = user;
    }

    public StockRecord(){}

    public int getId() {
        return id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Portfolio portfolio) {
        this.portfolio = portfolio;
    }

    @Override
    public String toString() {
        return "StockRecord{" +
                "id=" + id +
                ", ticker='" + ticker +
                ", quantity=" + quantity +
                ", user=" + user +
                '}';
    }
}
