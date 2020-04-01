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
    private String tanggalTask;
    @ColumnInfo(name = "catatanTask")
    private String catatanTask;

    // Constructor.
    public Task() {

    }

    // Setter
    public void setTanggalTask(String tanggalTask){
        this.tanggalTask = tanggalTask;
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

    // Getter.
    public String getTanggalTask() {
        return getTanggalTask();
    }

    long getRowId() {
        return rowId;
    }

    String getJudulTask() {
        return judulTask;
    }

    String getCatatanTask() {
        return catatanTask;
    }

    String getTanggal() {
        return tanggalTask.substring(0, 2);
    }

    String getBulan() {
        return tanggalTask.substring(3, 6);
    }

    String getTahun() {
        return tanggalTask.substring(7);
    }
}