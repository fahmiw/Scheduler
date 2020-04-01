package com.example.trelli.Helper;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.trelli.Adapter.TaskRepository;
import com.example.trelli.Model.Task;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    private TaskRepository taskRepository;
    private LiveData<List<Task>> allTask;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
        allTask = taskRepository.getAllTask();
    }

    public LiveData<List<Task>> getAllTask(){
        return allTask;
    }

    public void insert(Task task){
        taskRepository.insert(task);
    }

    public void  delete(Task taskId){
        taskRepository.delete(taskId);
    }
    public void update(Task task){
        taskRepository.update(task);
    }
}
