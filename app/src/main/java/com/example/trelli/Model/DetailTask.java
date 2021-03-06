package com.example.trelli.Model;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.trelli.Adapter.TaskRepository;
import com.example.trelli.Helper.TaskViewModel;
import com.example.trelli.R;

public class DetailTask extends AppCompatActivity {
    private TaskViewModel taskViewModel;
    TextView vJudulTask, vTanggalTask, vCatatanTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Lakukan Task-Mu!!");
        Bundle bundle = getIntent().getExtras();
        setContentView(R.layout.detailtask_activity);

        vJudulTask = findViewById(R.id.detail_judul);
        vTanggalTask = findViewById(R.id.detail_tanggal);
        vCatatanTask = findViewById(R.id.detail_catatan);

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        assert bundle != null;
            vJudulTask.setText(bundle.getString("judulTask"));
            vTanggalTask.setText(bundle.getString("tanggalTask"));
            vCatatanTask.setText(bundle.getString("catatanTask"));

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        // Pop up Task
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.8), (int) (height*.6));
    }
}
