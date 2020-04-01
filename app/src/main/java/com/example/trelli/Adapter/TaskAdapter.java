package com.example.trelli.Adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.example.trelli.Model.DetailTask;
import com.example.trelli.Model.Task;
import com.example.trelli.R;

import java.util.List;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {
    private List<Task> listTask;
    private LayoutInflater layoutInflater;

    public void setListTask(List<Task> list){
        this.listTask = list;
        notifyDataSetChanged();
    }
    public TaskAdapter(Context context){
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, final int position) {
        final Task task = listTask.get(position);
        holder.cardView.setBackgroundColor(Color.rgb(255, 255, 255));
        holder.JudulTask.setText(task.getJudulTask());
        holder.tanggalTask.setText(task.getTanggal());
        holder.bulanTask.setText(task.getBulan());
        holder.tahunTask.setText(task.getTahun());
        holder.catatanTask.setText(task.getCatatanTask());
        holder.itemView.setOnClickListener(v -> {
            // Pass data
            Bundle bundle = new Bundle();
            bundle.putLong("id", getItemId(position));
            bundle.putString("judulTask", task.getJudulTask());
            bundle.putString("tanggalTask", task.getTanggal() + "-" + task.getBulan() + "-" + task.getTahun());
            bundle.putString("catatanTask", task.getCatatanTask());
            Intent intent = new Intent(v.getContext(), DetailTask.class);
            intent.putExtras(bundle);
            v.getContext().startActivity(intent);
        });
}
    @Override
    public int getItemCount() {
        return listTask.size();
    }

    class TaskHolder extends RecyclerView.ViewHolder {
        TextView JudulTask, tanggalTask, bulanTask, tahunTask,catatanTask;
        CardView cardView;
        TaskHolder(@NonNull View itemView) {
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

}

