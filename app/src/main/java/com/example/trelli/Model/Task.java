package com.example.trelli.Model;

import android.annotation.SuppressLint;

import java.util.Date;

public class Task{
    private long id;
    private String judulTask;
    private Date dateTask;
    private String catatanTask;


    public Task(String pJudulTask, Date pDateTask, String pCatatanTask){
        this.judulTask      = pJudulTask;
        this.dateTask       = pDateTask;
        this.catatanTask    = pCatatanTask;
    }

    public Task(long pId, String pJudulTask, Date pDateTask, String pCatatanTask){
        this.id             = pId;
        this.judulTask      = pJudulTask;
        this.dateTask    = pDateTask;
        this.catatanTask    = pCatatanTask;
    }

    public long getId() {
        return id;
    }


    public String getJudulTask(){
        return judulTask;
    }

    @SuppressLint("DefaultLocale")
    public String getDateTask() {
        return String.format("%td-%tb-%tY", this.dateTask, this.dateTask, this.dateTask);
    }

    public String getCatatanTask() {
        return catatanTask;
    }

    @SuppressLint("DefaultLocale")
    public String getTgl(){
        return String.format("%td",this.dateTask);
    }

    public String getBulan(){
        return String.format("%tb",this.dateTask).toUpperCase();
    }

    public String getTahun(){
        return String.format("%tY",this.dateTask);
    }
}
