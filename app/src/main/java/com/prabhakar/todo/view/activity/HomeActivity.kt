package com.prabhakar.todotaskmanager.view.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.prabhakar.todo.R
import com.prabhakar.todo.database.TaskDAO
import com.prabhakar.todo.database.TaskRoomDB
import com.prabhakar.todo.database.model.TaskModel
import com.prabhakar.todo.database.repository.TaskRepo
import com.prabhakar.todo.view.ClickListener
import com.prabhakar.todo.view.adapter.TaskAdapter
import com.prabhakar.todo.view.fragment.AddFragment
import com.prabhakar.todo.view.fragment.EditFragment
import com.prabhakar.todo.viewmodel.TaskViewModel
import com.prabhakar.todo.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), ClickListener {

    private val fragmentManager = supportFragmentManager
    private lateinit var viewModel: TaskViewModel
    private lateinit var viewModelFactory: TaskViewModelFactory
    private lateinit var repo: TaskRepo
    private lateinit var taskDAO: TaskDAO
    private lateinit var roomDB: TaskRoomDB
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


        // Set user name

//        if (intent != null) {
//            user_name.text = "Welcome ${intent.getStringExtra("userName")}"
//        } else {
//            user_name.text = "Welcome"
//        }
        // Add Task

        addBtn.setOnClickListener {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.homeActivity, AddFragment()).addToBackStack("AddFragment")
                .commit()

            addBtn.visibility = View.GONE
        }
        buildList()
        setRecyclerView()

    }


    private fun buildList() {
        viewModel.showAllTask().observe(this, Observer {
            taskList.clear()
            taskList.addAll(it)
            taskAdapter.notifyDataSetChanged()
            emptyListHandler()
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
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.homeActivity, EditFragment(taskModel))
            .addToBackStack("EditFragment").commit()
        addBtn.visibility = View.GONE
    }

    override fun onClickDelete(taskModel: TaskModel, position: Int) {
        viewModel.deleteTask(taskModel)
    }


    // Check list is empty or not
    private fun emptyListHandler() {
        if (taskList.isNullOrEmpty()) {
            taskRecyclerView.visibility = View.GONE
            emptyTask.visibility = View.VISIBLE
            homeActivity.setBackgroundColor(Color.WHITE)
        } else {
            taskRecyclerView.visibility = View.VISIBLE
            emptyTask.visibility = View.GONE
            homeActivity.setBackgroundResource(R.drawable.home_bg)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        addBtn.visibility = View.VISIBLE
    }
}