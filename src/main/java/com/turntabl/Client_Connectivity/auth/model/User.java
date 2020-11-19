package com.turntabl.Client_Connectivity.auth.model;

//importing necessary libraries
import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "\"User\"")
public class User {

    //user class attributes
    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Portfolio> portfolios = new ArrayList<>();


    //user constructor
    public User() {
    }

    public User(String name, String email, String password, Role role, Portfolio portfolio) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.portfolios.add(portfolio);
    }

    //user constructor which takes user email and password.
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }


    /***
     * Getters and Setters
     * @return
     */


    public Long getId(){
        return  this.id;
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

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    /**
     * Methods
     * @return
     */
    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                "\nname=" + name +
                "\nemail=" + email +
                '}';
    }

}
