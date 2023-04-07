package com.cognixia.jump.tutorcapstone.model;

import java.io.Serializable;

//model object used to send a username and password for grabing and using tokens

public class AuthenticationRequest implements Serializable{
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;

    

    public AuthenticationRequest() {
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}
