package app.prabhakar.todotaskmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import app.prabhakar.todotaskmanager.database.model.TaskModel
import app.prabhakar.todotaskmanager.database.repository.TaskRepo

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