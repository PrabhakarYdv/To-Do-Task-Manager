package com.prabhakar.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prabhakar.todo.database.model.TaskModel
import com.prabhakar.todo.database.repository.TaskRepo

class TaskViewModel(private val repo: TaskRepo) : ViewModel() {

    fun showAllTask(): LiveData<MutableList<TaskModel>> {
        return repo.getAllTask()
    }

    fun addTask(taskModel: TaskModel) {
        repo.insertTask(taskModel)
    }

    fun editTask(taskModel: TaskModel) {
        repo.updateTask(taskModel)
    }

    fun deleteTask(taskModel: TaskModel) {
        repo.deleteTask(taskModel)
    }

    fun deleteAllTask(taskModel: TaskModel) {
        repo.deleteAllTask()
    }
}