package com.turntabl.Client_Connectivity.clientorder.model;

public class UserOrderResponse {
    private Long user_id;
    private ClientOrder order;

    public UserOrderResponse(){}

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public ClientOrder getOrder() {
        return order;
    }

    public void setOrder(ClientOrder order) {
        this.order = order;
    }
}
