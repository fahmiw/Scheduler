package com.example.trelli.Adapter;

//import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.trelli.R;
import com.example.trelli.Model.Task;

import  java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> dataList;

    public TaskAdapter(ArrayList<Task> dataList){
        this.dataList=dataList;
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtTgl.setText( dataList.get(position).getTgl());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private TextView txtJudul, txtTgl, txtCatatan;

        public TaskViewHolder(View itemView) {
            super(itemView);
            txtJudul = (TextView) itemView.findViewById(R.id.txt_judul);
            txtTgl = (TextView) itemView.findViewById(R.id.txt_tgl);
            txtCatatan = (TextView) itemView.findViewById(R.id.txt_catatan);
        }
    }
}

