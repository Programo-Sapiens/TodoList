package com.bawp.todoister.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bawp.todoister.R;
import com.bawp.todoister.model.Task;
import com.google.android.material.chip.Chip;

import java.util.List;

public class Adapter_Todo extends RecyclerView.Adapter<Adapter_Todo.viewholder> {
    private final List<Task> taskList;

    public Adapter_Todo(List<Task> taskList) {
        this.taskList = taskList;
    }


    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_row,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        Task task=taskList.get(position);
        holder.task.setText(task.getTask());




    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
public AppCompatRadioButton radioButton;
public AppCompatTextView task;
public Chip todayChip;
        public viewholder(@NonNull View itemView) {
            super(itemView);

            radioButton=itemView.findViewById(R.id.todo_radio_button);
            task=itemView.findViewById(R.id.todo_row_todo);
            todayChip=itemView.findViewById(R.id.today_chip);
        }
    }
}
