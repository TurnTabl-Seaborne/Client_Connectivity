package com.turntabl.Client_Connectivity.auth.model;

import com.turntabl.Client_Connectivity.portfolio.model.Portfolio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserResponse {

    private long user_id;
    private String name;
    private String email;
    private String role;

    private List<Integer> portfolio_id;

    public UserResponse(){
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getPortfolio_id() {
        return portfolio_id;
    }

    public void setPortfolio_id(List<Integer> portfolio_id) {
        this.portfolio_id = portfolio_id;
    }
}
