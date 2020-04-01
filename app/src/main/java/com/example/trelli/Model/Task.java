package com.example.trelli.Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "table_task")
public
class Task {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idTask")
    private long rowId;
    @ColumnInfo(name = "judulTask")
    private String judulTask;
    @ColumnInfo(name = "tanggalTask")
    private String dateTask;
    @ColumnInfo(name = "catatanTask")
    private String catatanTask;

    public Task() { }

    public void setDateTask(String tanggalTask){
        this.dateTask = tanggalTask;
    }

    public void setRowId(long rowId) {
        this.rowId = rowId;
    }

    public void setJudulTask(String judulTask){
        this.judulTask = judulTask;
    }

    public void setCatatanTask(String catatanTask) {
        this.catatanTask = catatanTask;
    }

    public String getDateTask() {
        return dateTask;
    }

    public long getRowId() {
        return rowId;
    }

    public String getJudulTask() {
        return judulTask;
    }

    public String getCatatanTask() {
        return catatanTask;
    }

    public String getTanggal() {
        return dateTask.substring(0, 2);
    }

    public String getBulan() {
        return dateTask.substring(3, 6);
    }

    public String getTahun() {
        return dateTask.substring(7);
    }
}