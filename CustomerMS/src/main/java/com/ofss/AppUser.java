package com.ofss;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "APP_USER_CUSTOMERMS")   // Map this entity to APP_USER table
public class AppUser {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ROLES")
    private String roles; // comma-separated roles

    // Default constructor
    public AppUser() {
        super();
    }

    // Parameterized constructor
    public AppUser(long id, String userName, String password, String roles) {
        super();
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    // Getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
