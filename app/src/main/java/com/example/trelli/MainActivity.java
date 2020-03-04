package com.example.trelli;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private ArrayList<Task> taskArrayList;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        adapter = new TaskAdapter(taskArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addData() {

    taskArrayList = new ArrayList<>();
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));
        taskArrayList.add(new Task("PR MATEMATIKA", "20-10-2020", "Harus selesai Sekarang" ));


    }
}