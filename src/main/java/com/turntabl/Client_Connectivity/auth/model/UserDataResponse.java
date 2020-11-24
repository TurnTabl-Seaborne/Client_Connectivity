package com.turntabl.Client_Connectivity.auth.model;

public class UserDataResponse {
    private String status;
    private int code;
    private UserData data;


    public UserDataResponse(){
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
