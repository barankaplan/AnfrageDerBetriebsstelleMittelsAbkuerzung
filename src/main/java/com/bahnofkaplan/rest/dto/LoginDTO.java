package com.bahnofkaplan.rest.dto;


import lombok.Data;

public class LoginDTO {
    private String userNameOrEMail;
    private String password;

    public String getUserNameOrEMail() {
        return userNameOrEMail;
    }

    public void setUserNameOrEMail(String userNameOrEMail) {
        this.userNameOrEMail = userNameOrEMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
