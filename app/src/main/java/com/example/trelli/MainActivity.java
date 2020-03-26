package com.example.trelli;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.trelli.Adapter.TaskAdapter;
import com.example.trelli.Helper.DbAdapter;
import com.example.trelli.Model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity{

    public static final int REQUEST_CODE_ADD = 1;
    DbAdapter db = new DbAdapter(this);
    FloatingActionButton addButton;
    private ArrayList<Task> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectAllTaskFromDatabase();
        db.open();
        Cursor c = db.getAllTask();
        if(c.moveToFirst()){
            do{
                DisplayDb(c);
            } while (c.moveToNext());
        }
        db.close();

        //Adapter
        adapter = new TaskAdapter(list, this);
        // Implementasi RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


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
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(MainActivity.this, NewTaskActivity.class);
                startActivityForResult(moveIntent, REQUEST_CODE_ADD);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ADD:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String judulTask = bundle.getString("judulTask");
                    String tanggalTask = bundle.getString("tanggalTask");
                    String catatanTask = bundle.getString("catatanTask");
                    Task pTask = null;
                    try {
                        pTask = new Task(judulTask, tanggalTask, catatanTask);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    // Coba ke DB.
                    insertTaskToDatabase(pTask);
                    selectAllTaskFromDatabase();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(this, "Data berhasil disimpan.", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Tambah Task dibatalkan.", Toast.LENGTH_LONG).show();
                }
        }
    }

    // Insert data Task ke dalam Database.
    private void insertTaskToDatabase(Task pTask){
        db.open();
        long id = db.insertTask(pTask.getJudulTask(), pTask.getTanggal(), pTask.getCatatanTask());
        db.close();
    }

    // Select semua data dari Database.
    private void selectAllTaskFromDatabase(){
        db.open();
        Cursor c = db.getAllTask();
        if(c.moveToFirst()){
            do {
                try {
                    list.add(new Task(c.getString(0),c.getString(1),c.getString(2),c.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while (c.moveToNext());
        }
        db.close();
    }
    public void DisplayDb(Cursor c){
        Toast.makeText(this,"id: " + c.getString(0) + "\n" +
                                            "Judul: " + c.getString(1) + "\n" +
                                            "Tanggal: " + c.getString(2) + "\n" +
                                            "Catatan: " + c.getString(3) + "\n", Toast.LENGTH_LONG).show();
    }

}
