package com.example.trelli.Model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trelli.Adapter.TaskAdapter;
import com.example.trelli.Helper.TaskViewModel;
import com.example.trelli.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import java.util.List;


public class MainActivity extends AppCompatActivity{

    List<Task> list;
    private TaskViewModel taskViewModel;
    public static final int REQUEST_CODE_ADD = 1;
    private RecyclerView recyclerView;
    TaskAdapter adapter;
    FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Adapter
        adapter = new TaskAdapter(this);

        // Implementasi RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        // Instansiasi View model dan Database
        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);
        taskViewModel.getAllTask().observe(this, tasks -> {
            adapter.setListTask(tasks);
            list = tasks;
        });

        //DeleteTask
        deleteTask();

        // Menambah Task
        setupAddButton();
    }

// ARRAY VERSION
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    private void addData() {
//
//    taskArrayList = new ArrayList<>();
//        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
//        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
//        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
//        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
//        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
//
//    }

    // Jika tombol Add task di tap.
    private void setupAddButton(){
        addButton = findViewById(R.id.tombol);
        addButton.setOnClickListener(v ->  {
                Intent moveIntent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivityForResult(moveIntent, REQUEST_CODE_ADD);
        });
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD) {
            if (resultCode == Activity.RESULT_OK) {
                assert data != null;
                Bundle bundle = data.getExtras();
                assert bundle != null;
                String judulTask = bundle.getString("judulTask");
                String tanggalTask = bundle.getString("tanggalTask");
                String catatanTask = bundle.getString("catatanTask");
                Task task = new Task();
                task.setJudulTask(judulTask);
                task.setDateTask(tanggalTask);
                task.setCatatanTask(catatanTask);

                taskViewModel.insert(task);
                Toast.makeText(this, "Task berhasil Disimpan", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Menambah Task Dibatalkan", Toast.LENGTH_LONG).show();
            }
        }
    }

    // Delete Task Dari Database
    private void deleteTask(){
        SwipeController swipeController = new SwipeController(this){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
                final Task task = list.get(position);
                Task task1 = new Task();
                task1.setRowId(task.getRowId());
                task1.setJudulTask(task.getJudulTask());
                task1.setDateTask(task.getDateTask());
                task1.setCatatanTask(task.getCatatanTask());
                adapter.removeItem(position);
                taskViewModel.delete(task);
                Snackbar snackbar = Snackbar.make(recyclerView, "Task Telah dihapus", Snackbar.LENGTH_LONG)
                        .setAction("UNDO", view -> taskViewModel.insert(task));
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        };
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}