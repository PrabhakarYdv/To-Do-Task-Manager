package com.prabhakar.todo.view

import com.prabhakar.todo.database.model.TaskModel

interface ClickListener {
    fun onClickComplete(taskModel: TaskModel, position: Int)
    fun onClickEdit(taskModel: TaskModel, position: Int)
    fun onClickDelete(taskModel: TaskModel, position: Int)
}