package com.example.trelli.Helper;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.trelli.Model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1,exportSchema = false)
public abstract class TaskRoomDatabase extends RoomDatabase{
    public abstract TaskDAO taskDAO();
    private static volatile TaskRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriterExec = Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    public static TaskRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (TaskRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskRoomDatabase.class, "task_database").build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback RoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db){
            super.onOpen(db);
            databaseWriterExec.execute(()->{
                TaskDAO dao = INSTANCE.taskDAO();
            });
        }
    };
}