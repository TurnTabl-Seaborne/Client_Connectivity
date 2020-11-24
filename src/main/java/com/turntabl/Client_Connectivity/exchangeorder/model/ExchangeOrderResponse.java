package com.turntabl.Client_Connectivity.exchangeorder.model;

public class ExchangeOrderResponse {
    private int client_order_id;
    private String order_key;

    public ExchangeOrderResponse() {

    }

    public int getClient_order_id() {
        return client_order_id;
    }

    public void setClient_order_id(int client_order_id) {
        this.client_order_id = client_order_id;
    }

    public String getOrder_key() {
        return order_key;
    }

    public void setOrder_key(String order_key) {
        this.order_key = order_key;
    }
}
