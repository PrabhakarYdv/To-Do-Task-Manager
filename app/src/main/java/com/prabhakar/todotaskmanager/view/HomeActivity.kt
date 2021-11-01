package com.prabhakar.todotaskmanager.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.TaskDAO
import com.prabhakar.todotaskmanager.database.TaskRoomDB
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.database.repository.TaskRepo
import com.prabhakar.todotaskmanager.view.adapter.TaskAdapter
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModel
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), ClickListener {
    private lateinit var viewModel: TaskViewModel
    lateinit var viewModelFactory: TaskViewModelFactory
    private lateinit var repo: TaskRepo
    private lateinit var taskDAO: TaskDAO
    lateinit var roomDB: TaskRoomDB
    private lateinit var taskAdapter: TaskAdapter

    private val taskList = mutableListOf<TaskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        roomDB = TaskRoomDB.getDBObject(this)
        taskDAO = roomDB.getDAO()
        repo = TaskRepo(taskDAO)
        viewModelFactory = TaskViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)


        // Add Task

        addBtn.setOnClickListener {
            startActivity(Intent(this, AddActivity::class.java))
        }


        buildList()
        setRecyclerView()

    }

    private fun buildList() {
        viewModel.showAllTask().observe(this, Observer {
            taskList.clear()
            taskList.addAll(it)
            taskAdapter.notifyDataSetChanged()
        })

    }


    private fun setRecyclerView() {
        taskAdapter = TaskAdapter(taskList, this)
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        taskRecyclerView.adapter = taskAdapter
    }

    override fun onClickComplete(taskModel: TaskModel, position: Int) {
        viewModel.deleteTask(taskModel)
    }

    override fun onClickEdit(taskModel: TaskModel, position: Int) {
        val intent = Intent(this, EditActivity::class.java)
        startActivity(intent)
    }

    override fun onClickDelete(taskModel: TaskModel, position: Int) {
        viewModel.deleteTask(taskModel)
    }

    // Check list is empty or not
    fun emptyListHandler() {
        if (taskList.isNullOrEmpty()) {
            taskRecyclerView.visibility = View.GONE
            emptyTask.visibility = View.VISIBLE
            homeActivity.setBackgroundColor(Color.WHITE)
        }
    }
}