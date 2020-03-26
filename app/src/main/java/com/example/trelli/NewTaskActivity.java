package com.example.trelli;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class NewTaskActivity extends AppCompatActivity {

    private EditText mJudulTask, mCatatanTask;
    private TextView mDueDateTask;

    protected void onCreate(Bundle savedIntanceState) {
        setTitle("Tambah Task!!");
        super.onCreate(savedIntanceState);
        setContentView(R.layout.addnewtask_activity);
        setDateButton();
        saveTask();

//        DisplayMetrics dm = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//        // Pop up Task
//        int width = dm.widthPixels;
//        int height = dm.heightPixels;
//        getWindow().setLayout((int) (width*.8), (int) (height*.6));

    }

    private void setDateButton() {
        ImageView selectDate = (ImageView)findViewById(R.id.setDate);
        this.mDueDateTask = (TextView)findViewById(R.id.text_input_date);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(NewTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        Calendar newDate = Calendar.getInstance();
                        newDate.set(year, month, day);
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);
                        mDueDateTask.setText(dateFormatter.format(newDate.getTime()).toUpperCase());
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });

    }

    private void saveTask() {
        Button saveBtn = (Button) findViewById(R.id.saveButton);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mJudulTask = (EditText) findViewById(R.id.text_input_judul);
                mCatatanTask = (EditText) findViewById(R.id.text_input_catatan);
                String sJudulTask = mJudulTask.getText().toString();
                String sTanggalTask = mDueDateTask.getText().toString();
                String sCatatanTask = mCatatanTask.getText().toString();
                // Pass data.
                if (sJudulTask.equals("") || sTanggalTask.equals("") || sCatatanTask.equals("")) {
                    Toast.makeText(NewTaskActivity.this, "Data task kurang lengkap.", Toast.LENGTH_SHORT).show();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putString("judulTask", sJudulTask);
                    bundle.putString("tanggalTask", sTanggalTask);
                    bundle.putString("catatanTask", sCatatanTask);
                    Intent moveIntent = new Intent();
                    moveIntent.putExtras(bundle);
                    setResult(Activity.RESULT_OK, moveIntent);
                    finish();
                }
            }
        });
    }
}
