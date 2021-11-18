package com.prabhakar.todo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabhakar.todo.database.repository.TaskRepo


class TaskViewModelFactory(private val repo: TaskRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repo) as T
    }

}