package com.example.doit2.security;

import java.io.Serializable;

public class AuthRequest implements Serializable {

    private static final long serialVersionUID = 2905671811842088210L;

    private String userName;
    private String password;

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

    public AuthRequest() {
    }

}
