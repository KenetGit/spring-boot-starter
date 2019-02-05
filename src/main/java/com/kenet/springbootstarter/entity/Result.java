package com.kenet.springbootstarter.entity;

import java.io.Serializable;

public class Result implements Serializable{

    private boolean success;
    private String message;

    public Result(){}

    public Result(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
