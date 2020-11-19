package com.turntabl.Client_Connectivity.order.model;

import javax.xml.bind.annotation.XmlElement;

public class Order {
    protected int portfolioId;
    protected String ticker;
    protected int quantity;
    protected String side;
    protected double price;
    protected String algorithm;
    protected String status;
    protected String state;
}
