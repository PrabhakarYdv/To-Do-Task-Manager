package com.prabhakar.todotaskmanager.view

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.TaskDAO
import com.prabhakar.todotaskmanager.database.TaskRoomDB
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.database.repository.TaskRepo
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModel
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity : AppCompatActivity() {
    private lateinit var viewModel: TaskViewModel
    lateinit var viewModelFactory: TaskViewModelFactory
    private lateinit var repo: TaskRepo
    private lateinit var taskDAO: TaskDAO
    lateinit var roomDB: TaskRoomDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
//        editTask_title.text = taskModel.title.toString() as Editable

        roomDB = TaskRoomDB.getDBObject(this)
        taskDAO = roomDB.getDAO()
        repo = TaskRepo(taskDAO)
        viewModelFactory = TaskViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)

    }
}