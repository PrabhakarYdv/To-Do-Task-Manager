package com.prabhakar.todotaskmanager.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.adapter.TaskAdapter
import com.prabhakar.todotaskmanager.database.TaskDAO
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.database.repository.TaskRepo
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModel
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
   private lateinit var viewModel:TaskViewModel
   private lateinit var repo: TaskRepo
   private lateinit var taskDAO: TaskDAO
   private lateinit var taskAdapter: TaskAdapter

    private val taskList= mutableListOf<TaskModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        buildList()
        setRecyclerView()

    }

    private fun buildList(){
        taskList.add(TaskModel(0,"Test","Sample Test","30-10-2021"))
    }
    private fun setRecyclerView(){
        taskAdapter= TaskAdapter(taskList)
        taskRecyclerView.layoutManager=LinearLayoutManager(this)
        taskRecyclerView.adapter=taskAdapter
    }
}