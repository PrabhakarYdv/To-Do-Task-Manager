package com.prabhakar.todotaskmanager.view

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.TaskDAO
import com.prabhakar.todotaskmanager.database.TaskRoomDB
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.database.repository.TaskRepo
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModel
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.activity_add.*
import java.util.*

class AddActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    private var date = "DD-MM-YYYY"
    private lateinit var viewModel: TaskViewModel
    lateinit var viewModelFactory: TaskViewModelFactory
    private lateinit var repo: TaskRepo
    private lateinit var taskDAO: TaskDAO
    lateinit var roomDB: TaskRoomDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        roomDB = TaskRoomDB.getDBObject(this)
        taskDAO = roomDB.getDAO()
        repo = TaskRepo(taskDAO)
        viewModelFactory = TaskViewModelFactory(repo)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TaskViewModel::class.java)


        // Pick Date ------------------
        addTask_date.setOnClickListener {
            dateSelect()
        }

        // Add Task to Database
        saveBtn.setOnClickListener {
            if (!addTask_title.text.isNullOrEmpty()
                && !addTask_desc.text.isNullOrEmpty()
                && !addTask_date.text.equals("DD-MM-YYYY")
            ) {
                val taskModel = TaskModel()
                taskModel.title = addTask_title.text.toString()
                taskModel.desc = addTask_desc.text.toString()
                taskModel.date = date
                viewModel.addTask(taskModel)
                startActivity(Intent(this, HomeActivity::class.java))
            } else {
                AlertDialog.Builder(this).setTitle("Add Your Task")
                    .setMessage("Please Enter Your Task or Pending work")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ -> }).show()
            }
        }
    }

    private fun dateSelect() {
        DatePickerDialog(
            this, this,
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        date = "$dayOfMonth-$month-$year"
        addTask_date.text = date
    }
}