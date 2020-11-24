package com.turntabl.Client_Connectivity.clientorder.model;

public class SendOrderResponse {
    private int statusCode;
    private String message;
    private ClientOrder data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClientOrder getData() {
        return data;
    }

    public void setData(ClientOrder data) {
        this.data = data;
    }
}
