package com.secretaria.ADPorto.entity;

import java.util.ArrayList;
import java.util.List;

//izau
public class   User {

    private String userName;
    private String password;
    private List<String> roles; //= new ArrayList<>();

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
