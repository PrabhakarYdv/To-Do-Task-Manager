package com.prabhakar.todotaskmanager.view.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.view.ClickListener
import kotlinx.android.synthetic.main.task_layout.view.*

class TaskViewHolder(private val view: View, private val onClickListener: ClickListener) :
    RecyclerView.ViewHolder(view) {

    fun setTaskData(model: TaskModel) {
        view.apply {
            title.text = model.title
            desc.text = model.desc
            date.text = model.date

            // Button Click Listener ===> Complete , Edit , Delete

            btnComplete.setOnClickListener {
                onClickListener.onClickComplete(model, adapterPosition)
            }

            btnEdit.setOnClickListener {
                onClickListener.onClickEdit(model, adapterPosition)
            }

            btnDelete.setOnClickListener {
                onClickListener.onClickDelete(model, adapterPosition)
            }
        }

    }
}