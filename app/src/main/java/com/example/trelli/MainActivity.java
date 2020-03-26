package com.example.trelli;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.trelli.Adapter.TaskAdapter;
import com.example.trelli.Helper.DbAdapter;
import com.example.trelli.Model.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity{

    public static final int REQUEST_CODE_ADD = 1;
    DbAdapter db = new DbAdapter(this);
    FloatingActionButton addButton;
    private ArrayList<Task> list = new ArrayList<>();
    private RecyclerView recyclerView;
    TaskAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selectAllTaskFromDatabase();

//        db.open();
//        Cursor c = db.getAllTask();
//        if(c.moveToFirst()){
//            do{
//                DisplayDb(c);
//            } while (c.moveToNext());
//        }
//        db.close();

        //Adapter
        adapter = new TaskAdapter(list, this);
        // Implementasi RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        deleteTask();
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

    @SuppressLint("SimpleDateFormat")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_ADD:
                if (resultCode == Activity.RESULT_OK) {
                    assert data !=null;
                    Bundle bundle = data.getExtras();
                    assert bundle !=null;
                    String judulTask = bundle.getString("judulTask");
                    String tanggalTask = bundle.getString("tanggalTask");
                    String catatanTask = bundle.getString("catatanTask");
                    Date aDateTask = null;
                    try {
                        assert tanggalTask != null;
                        aDateTask = new SimpleDateFormat("dd-MMM-yyyy").parse(tanggalTask);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    Task pTask = new Task(judulTask, aDateTask, catatanTask);
                    // Insert
                    insertTaskToDatabase(pTask);
                    list.add(pTask);
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
        String dateTask;
        dateTask = pTask.getDateTask();
        db.insertTask(pTask.getJudulTask(), dateTask, pTask.getCatatanTask());
        db.close();
    }

    // Select semua data dari Database.
    private void selectAllTaskFromDatabase(){
        db.open();
        Cursor c = db.getAllTask();
        if(c.moveToFirst()){
            do {
                try {
                    String date = c.getString(2);
                    @SuppressLint("SimpleDateFormat") Date aDate = new SimpleDateFormat("dd-MMM-yyyy").parse(date);
                    list.add(new Task(c.getLong(0),c.getString(1), aDate, c.getString(3)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }while (c.moveToNext());
        }
        db.close();
    }

    // Delete
    private void deleteTask(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                final String mJudulTask = list.get(position).getJudulTask();
                final String mTanggalTask = list.get(position).getDateTask();
                final String mCatatanTask = list.get(position).getCatatanTask();

                db.open();
                db.deleteContact(list.get(position).getId());
                db.close();


                list.remove(position);
                Toast.makeText(MainActivity.this, "Item Removed" + position, Toast.LENGTH_SHORT).show();
                adapter.notifyItemRemoved(position);


                Snackbar.make(recyclerView, mJudulTask, Snackbar.LENGTH_LONG)
                        .setAction("Undo", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Task aTask = null;
                                try {
                                    String date = mTanggalTask;
                                    Date aDate = new SimpleDateFormat("dd-MMM-yyyy").parse(date);
                                    aTask = new Task(mJudulTask, aDate, mCatatanTask);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                db.open();
                                db.insertTask(String.valueOf(position), mJudulTask, mTanggalTask, mCatatanTask);
                                db.close();

                                list.add(position, aTask);
                                adapter.notifyItemInserted(position);
                            }
                        }).show();
            }
        }).attachToRecyclerView(recyclerView);


    }

}
