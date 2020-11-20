package com.turntabl.Client_Connectivity.exchangeorder.model;

import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "exchangeOrders")
public class ExchangeOrder {
    @Id @GeneratedValue
    private int id;
    private String orderKey;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<ClientOrder> orders;
    @OneToOne(cascade = CascadeType.ALL)
    private ClientOrder order;

    public ExchangeOrder(String orderKey, ClientOrder order) {
        this.orderKey = orderKey;
        this.order = order;
    }

    public ExchangeOrder(){}

    public int getId() {
        return id;
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

    @Override
    public String toString() {
        return "ExchangeOrder{" +
                "id=" + id +
                ", orderKey='" + orderKey + '\'' +
                ", order=" + order +
                '}';
    }
}
