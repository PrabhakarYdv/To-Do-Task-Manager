package com.prabhakar.todotaskmanager.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.prabhakar.todotaskmanager.database.model.TaskModel
import kotlinx.android.synthetic.main.task_layout.view.*

class TaskViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun setTaskData(model: TaskModel) {
        view.apply {
            title.text = model.title
            desc.text = model.desc
            date.text = model.date

            btn_read.setOnClickListener {

            }

            btn_edit.setOnClickListener {

            }

            btn_delete.setOnClickListener {

            }
        }

    }
}