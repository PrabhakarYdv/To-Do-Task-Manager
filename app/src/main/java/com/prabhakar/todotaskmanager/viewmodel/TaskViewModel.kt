package com.prabhakar.todotaskmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.database.repository.TaskRepo

class TaskViewModel(private val repo: TaskRepo) : ViewModel() {

    fun showAllTask(): LiveData<MutableList<TaskModel>> {
        return repo.getAllTask()
    }

    fun addTask(taskModel: TaskModel){
        repo.insertTask(taskModel)
    }

    fun editTask(taskModel: TaskModel){
        repo.updateTask(taskModel)
    }
    fun deleteTask(taskModel: TaskModel) {
        repo.deleteTask(taskModel)
    }

    fun deleteAllTask(taskModel: TaskModel) {
        repo.deleteAllTask()
    }
}