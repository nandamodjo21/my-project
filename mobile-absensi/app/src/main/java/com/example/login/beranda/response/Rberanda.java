package com.example.login.beranda.response;

import java.util.List;

public class Rberanda {

    private String status,messege;
    private List<Ra_beranda> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessege() {
        return messege;
    }

    public void setMessege(String messege) {
        this.messege = messege;
    }

    public List<Ra_beranda> getData() {
        return data;
    }

    public void setData(List<Ra_beranda> data) {
        this.data = data;
    }

}
