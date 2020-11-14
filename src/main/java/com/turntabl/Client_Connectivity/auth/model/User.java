package com.turntabl.Client_Connectivity.auth.model;

//importing necessary libraries
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "\"User\"")
public class User {

    //user class attributes
    private @Id @GeneratedValue Long id;
    private String name;
    private String email;
    private String password;


    //user constructor
    public User() {
    }


    //user constructor which takes user name, email and password.
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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

    public void setId(Long id){ this.id = id;}

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
