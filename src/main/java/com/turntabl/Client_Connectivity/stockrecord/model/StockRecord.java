package com.turntabl.Client_Connectivity.stockrecord.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stockRecord")
public class StockRecord implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "stock_record_id")
    private Integer stockRecordId;

    @Column(name = "product")
    private String ticker;

    @Column(name = "quantity")
    private int quantity;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    public StockRecord(String ticker, int quantity, User user) {
        this.ticker = ticker;
        this.quantity = quantity;
        this.user = user;
    }

    public StockRecord(){}

    public int getStockRecordId() {
        return stockRecordId;
    }

    public void setStockRecordId(int stockRecordId) {
        this.stockRecordId = stockRecordId;
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
}
