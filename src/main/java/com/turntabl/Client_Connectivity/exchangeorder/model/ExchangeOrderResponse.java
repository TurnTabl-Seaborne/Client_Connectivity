package com.turntabl.Client_Connectivity.exchangeorder.model;

public class ExchangeOrderResponse {
    private int clientOrderId;
    private String key;

    public ExchangeOrderResponse() {

    }

    public int getClientOrderId() {
        return clientOrderId;
    }

    public void setClientOrderId(int clientOrderId) {
        this.clientOrderId = clientOrderId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
