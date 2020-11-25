package com.turntabl.Client_Connectivity.exchangeorder.model;

import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;

public class Data {
    private Long userId;
    private int portfolioId;
    private ClientOrderData clientOrder;

    public Data() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(int portfolioId) {
        this.portfolioId = portfolioId;
    }

    public ClientOrderData getClientOrder() {
        return clientOrder;
    }

    public void setClientOrder(ClientOrderData clientOrder) {
        this.clientOrder = clientOrder;
    }
}
