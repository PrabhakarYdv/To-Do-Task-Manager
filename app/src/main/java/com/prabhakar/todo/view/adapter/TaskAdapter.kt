package com.prabhakar.todo.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.prabhakar.todo.R
import com.prabhakar.todo.database.model.TaskModel
import com.prabhakar.todo.view.ClickListener
import com.prabhakar.todo.view.viewholder.TaskViewHolder

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