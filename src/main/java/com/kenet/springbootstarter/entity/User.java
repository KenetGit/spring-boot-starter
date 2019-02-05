package com.kenet.springbootstarter.entity;

import java.io.Serializable;

public class User implements Serializable {
    private Long id; //编号
    private String username; //用户名
    private String password; //密码
    //getter/setter


    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
