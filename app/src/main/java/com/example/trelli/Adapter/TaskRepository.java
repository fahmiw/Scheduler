package com.example.trelli.Adapter;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.trelli.Helper.TaskDAO;
import com.example.trelli.Helper.TaskRoomDatabase;
import com.example.trelli.Model.Task;

import java.util.List;

public class TaskRepository {
    private TaskDAO taskDAO;
    private LiveData<List<Task>> allTask;

    public TaskRepository(Application application){
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDAO = db.taskDAO();
        allTask = taskDAO.getAlphabetizedTasks();
    }

    public LiveData<List<Task>> getAllTask(){
        return allTask;
    }
    public void insert(Task task){
        TaskRoomDatabase.databaseWriterExec.execute(()->{
                taskDAO.insertTask(task);
        });
    }
    public void delete(Task task){
        TaskRoomDatabase.databaseWriterExec.execute(()->{
            taskDAO.deleteTask(task);
        });
    }
    public void update(Task task) {
        TaskRoomDatabase.databaseWriterExec.execute(() -> {
            taskDAO.updateTask(task);
        });
    }
}
