package app.prabhakar.todotaskmanager.view

import app.prabhakar.todotaskmanager.database.model.TaskModel

interface ClickListener {
    fun onClickComplete(taskModel: TaskModel, position: Int)
    fun onClickEdit(taskModel: TaskModel, position: Int)
    fun onClickDelete(taskModel: TaskModel, position: Int)
}