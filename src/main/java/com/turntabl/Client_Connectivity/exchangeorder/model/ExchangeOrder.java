package com.turntabl.Client_Connectivity.exchangeorder.model;

import com.turntabl.Client_Connectivity.auth.model.User;
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
@Entity
@Table(name = "exchangeOrders")
public class ExchangeOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exchange_order_id")
    private int exchangeOrderId;

    @Column(name = "order_key")
    private String orderKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_order_id", nullable = false)
    private ClientOrder order;

    public ExchangeOrder(String orderKey, ClientOrder order) {
        this.orderKey = orderKey;
        this.order = order;
    }

    public ExchangeOrder(){}

    public int getExchangeOrderId() {
        return exchangeOrderId;
    }

    public void setExchangeOrderId(int exchangeOrderId) {
        this.exchangeOrderId = exchangeOrderId;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public void setOrderKey(String orderKey) {
        this.orderKey = orderKey;
    }

    public ClientOrder getOrder() {
        return order;
    }

    public void setOrder(ClientOrder order) {
        this.order = order;
    }


    public void assignToClientOrder(ClientOrder clientOrder){
        this.order = clientOrder;
    }
}
