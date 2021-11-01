package com.prabhakar.todotaskmanager.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prabhakar.todotaskmanager.database.repository.TaskRepo


class TaskViewModelFactory(private val repo: TaskRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TaskViewModel(repo) as T
    }

}