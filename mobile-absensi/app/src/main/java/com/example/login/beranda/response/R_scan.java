package com.example.login.beranda.response;

public class R_scan {

    public String getKode_perawat() {
        return kode_perawat;
    }

    public void setKode_perawat(String kode_perawat) {
        this.kode_perawat = kode_perawat;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String kode_perawat;
  private String code;
  private String message;
}
