package com.salage.model;

/**
 * Created by User on 2/7/2017.
 */

public class LoginResponse {

    private LoginInfo data = new LoginInfo();
    private String response;

    public LoginInfo getData() {
        return data;
    }

    public void setData(LoginInfo data) {
        this.data = data;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
