package com.turntabl.Client_Connectivity.auth.model;

//importing necessary libraries
import com.turntabl.Client_Connectivity.clientorder.model.ClientOrder;
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;
import com.turntabl.Client_Connectivity.stockrecord.model.StockRecord;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "\"user\"")
public class User implements Serializable {

    //user class attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long UserId;

    @Column(name = "name", length = 200)
    private String name;

    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "password", length = 200)
    private String password;

    @Column(name = "role", length = 200)
    @Enumerated(EnumType.STRING)
    private Role role;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Portfolio> portfolio = new HashSet<>(0);

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<StockRecord> orders = new ArrayList<>();

    public User() {
    }



    public List<StockRecord> getOrders() {
        return orders;
    }

    public void setOrders(List<StockRecord> orders) {
        this.orders = orders;
    }

    /***
     * Getters and Setters
     * @return
     */


    public Long getUserId() {
        return UserId;
    }

    public void setUserId(Long userId) {
        UserId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<Portfolio> getPortfolio() {
        return portfolio;
    }

    public void setPortfolio(Set<Portfolio> portfolio) {
        this.portfolio = portfolio;
    }

    public void addPortfolio(Portfolio portfolio){
        this.portfolio.add(portfolio);
    }
}
