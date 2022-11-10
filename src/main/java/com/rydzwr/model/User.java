package com.rydzwr.model;

import com.sun.istack.NotNull;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.*;

import static java.util.Arrays.asList;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int active;

    private String roles = "";


    public User(String username, String password, String roles){
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.active = 1;
    }

    protected User(){}

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getActive() {
        return active;
    }

    public String getRoles() {
        return roles;
    }

    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
