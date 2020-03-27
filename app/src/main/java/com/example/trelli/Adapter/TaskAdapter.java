package com.example.trelli.Adapter;


import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trelli.Model.Task;
import com.example.trelli.R;

import java.util.ArrayList;

/**
 * Created by delaroy on 1/5/18.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private ArrayList<Task> listTask;
    private Context context;
    public TaskAdapter(ArrayList<Task> list, Context ctx){
        this.listTask = list;
        this.context = ctx;
    }
    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        final Task task = listTask.get(position);
        holder.cardView.setBackgroundColor(Color.rgb(255, 255, 255));
        holder.JudulTask.setText(task.getJudulTask());
        holder.tanggalTask.setText(task.getTgl());
        holder.bulanTask.setText(task.getBulan());
        holder.tahunTask.setText(task.getTahun());
        holder.catatanTask.setText(task.getCatatanTask());
}
    @Override
    public int getItemCount() {
        return listTask.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder {
        TextView JudulTask, tanggalTask, bulanTask, tahunTask,catatanTask;
        CardView cardView;
        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            cardView    = itemView.findViewById(R.id.card);
            JudulTask   = itemView.findViewById(R.id.txt_judul);
            catatanTask = itemView.findViewById(R.id.txt_catatan);
            tanggalTask = itemView.findViewById(R.id.txt_tgl);
            bulanTask   = itemView.findViewById(R.id.txt_bln);
            tahunTask   = itemView.findViewById(R.id.txt_thn);
        }
    }
    public void removeItem(int position) {
        listTask.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(Task item, int position) {

        listTask.add(position, item);
        notifyItemInserted(position);
    }
    public ArrayList<Task> getData() {
        return listTask;
    }
}

