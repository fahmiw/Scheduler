package com.example.trelli;

import java.util.Date;

public class Task {
    private String judul;
    private String tgl;
    private  String catatan;

    public Task(String judul, String tgl, String catatan){
        this.judul=judul;
        this.tgl=tgl;
        this.catatan=catatan;
    }

    public String getJudul() {
        return judul;
    }

    public String getTgl() {
        return tgl;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
