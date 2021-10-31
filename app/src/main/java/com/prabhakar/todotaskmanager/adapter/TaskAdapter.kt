package com.prabhakar.todotaskmanager.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.viewholder.TaskViewHolder

class TaskAdapter(private val taskLists:MutableList<TaskModel>): RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(R.layout.task_layout,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val model=taskLists[position]
        holder.setTaskData(model)
    }

    override fun getItemCount(): Int {
        return taskLists.size
    }
}