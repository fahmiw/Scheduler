package com.example.trelli.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task{
    private String id;
    private String judulTask;
    private String tanggalTask;
    private String catatanTask;
    private Date tempDate;

    public Task(String pJudulTask, String pTanggalTask, String pCatatanTask) throws ParseException{
        this.judulTask      = pJudulTask;
        this.tanggalTask    = pTanggalTask;
        this.catatanTask    = pCatatanTask;
        this.tempDate       = new SimpleDateFormat("dd-MMM-yy").parse(this.tanggalTask);
    }

    public Task(String pId, String pJudulTask, String pTanggalTask, String pCatatanTask) throws ParseException{
        this.id             = pId;
        this.judulTask      = pJudulTask;
        this.tanggalTask    = pTanggalTask;
        this.catatanTask    = pCatatanTask;
        this.tempDate       = new SimpleDateFormat("dd-MMM-yyyy").parse(this.tanggalTask);
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulTask(){
        return judulTask;
    }

    public void setJudulTask(String judulTask) {
        this.judulTask = judulTask;
    }
    public String getTanggalTask(){
        return tanggalTask;
    }

    public void setTempDate(String tempDate) throws ParseException {
        this.tanggalTask = tanggalTask;
        this.tempDate = new SimpleDateFormat("dd-MMM-yyyy").parse(this.tanggalTask);
    }
    public String getCatatanTask(){
        return catatanTask;
    }
    public String getTanggal(){
        return String.format("%td",this.tempDate);
    }

    public String getBulan(){
        return String.format("%tb",this.tempDate).toUpperCase();
    }

    public String getTahun(){
        return String.format("%tY",this.tempDate);
    }
}
