package com.prabhakar.todo.database.repository

import androidx.lifecycle.LiveData
import com.prabhakar.todo.database.TaskDAO
import com.prabhakar.todo.database.model.TaskModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class TaskRepo(private val taskDAO: TaskDAO) {

    fun getAllTask(): LiveData<MutableList<TaskModel>> {
        return taskDAO.getAllTask()
    }

    fun insertTask(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.insertTask(taskModel)
        }

    }

    fun updateTask(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.updateTask(taskModel)
        }

    }

    fun deleteTask(taskModel: TaskModel) {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.deleteTask(taskModel)
        }

    }

    fun deleteAllTask() {
        CoroutineScope(Dispatchers.IO).launch {
            taskDAO.deleteAllTask()
        }

    }
}