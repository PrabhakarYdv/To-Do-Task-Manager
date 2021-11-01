package com.prabhakar.todotaskmanager.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.view.ClickListener
import com.prabhakar.todotaskmanager.view.viewholder.TaskViewHolder

class TaskAdapter(
    private val taskLists: MutableList<TaskModel>,
    private val onClickListener: ClickListener
) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_layout, parent, false)
        return TaskViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val model = taskLists[position]
        holder.setTaskData(model)
    }

    override fun getItemCount(): Int {
        return taskLists.size
    }
}