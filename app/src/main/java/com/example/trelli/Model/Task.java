package com.example.trelli.Model;

import android.database.Cursor;

import static com.example.trelli.Model.TaskContract.getColumnInt;
import static com.example.trelli.Model.TaskContract.getColumnLong;
import static com.example.trelli.Model.TaskContract.getColumnString;

public class Task{
    public static final long NO_DATE = Long.MAX_VALUE;
    public static final long NO_ID = -1;

    public long id;
    public final String judul;
    public final String catatan;
    public final boolean isComplete;
    public final long dueDate;


    public Task(String judul, String catatan, long dueDate, boolean isComplete) {
        this.id = NO_ID;
        this.judul = judul;
        this.catatan = catatan;
        this.dueDate = dueDate;
        this.isComplete = isComplete;
    }

    public Task(String judul, String catatan, boolean isComplete){
        this(judul, catatan, NO_DATE, isComplete);
    }

    public Task(Cursor cursor){
        this.id = getColumnLong(cursor, TaskContract.TaskColumns._ID);
        this.judul = getColumnString(cursor, TaskContract.TaskColumns.JUDUL);
        this.catatan = getColumnString(cursor, TaskContract.TaskColumns.CATATAN);
        this.dueDate = getColumnLong(cursor, TaskContract.TaskColumns.DUE_DATE);
        this.isComplete = getColumnInt(cursor, TaskContract.TaskColumns.IS_COMPLETE) == 1;
    }

    /**
     * Return true if a due date has been set on this task.
     */
    public boolean hasDueDate(){
        return this.dueDate != Long.MAX_VALUE;
    }
}
