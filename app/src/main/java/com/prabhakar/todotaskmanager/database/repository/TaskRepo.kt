package com.prabhakar.todotaskmanager.database.repository

import androidx.lifecycle.LiveData
import com.prabhakar.todotaskmanager.database.TaskDAO
import com.prabhakar.todotaskmanager.database.model.TaskModel


class TaskRepo(private val taskDAO: TaskDAO) {

    fun getAllTask(): LiveData<MutableList<TaskModel>> {
        return taskDAO.getAllTask()
    }

    fun insertTask(taskModel: TaskModel) {
        taskDAO.insertTask(taskModel)
    }

    fun updateTask(taskModel: TaskModel) {
        taskDAO.updateTask(taskModel)
    }

    fun deleteTask(taskModel: TaskModel) {
        taskDAO.deleteTask(taskModel)
    }

    fun deleteAllTask() {
        taskDAO.deleteAllTask()
    }
}