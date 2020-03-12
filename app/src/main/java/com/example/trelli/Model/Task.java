package com.example.trelli.Model;

import java.util.Date;

public class Task {
    private String judul;
    private String tgl;
    public Task(String judul, String tgl, String catatan){
        this.judul=judul;
        this.tgl=tgl;
    }

    public String getJudul() {
        return judul;
    }

    public String getTgl() {
        return tgl;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }
}
