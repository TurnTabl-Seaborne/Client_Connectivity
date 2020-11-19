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
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientOrder> orders;

    public ExchangeOrder(String orderKey, ClientOrder order) {
        this.orderKey = orderKey;
        this.orders.add(order);
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

    public List<ClientOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<ClientOrder> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "ExchangeOrder{" +
                "id=" + id +
                ", orderKey='" + orderKey + '\'' +
                ", orders=" + orders +
                '}';
    }
}
