package com.eversoft.tourist_facilitator.login.entity;



public class LoginReqParam {
    String email;
    String password;

    public LoginReqParam(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
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
}
