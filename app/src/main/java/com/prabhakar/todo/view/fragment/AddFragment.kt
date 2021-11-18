package com.prabhakar.todo.view.fragment

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.prabhakar.todo.R
import com.prabhakar.todo.database.TaskDAO
import com.prabhakar.todo.database.TaskRoomDB
import com.prabhakar.todo.database.model.TaskModel
import com.prabhakar.todo.database.repository.TaskRepo
import com.prabhakar.todo.view.activity.HomeActivity
import com.prabhakar.todo.viewmodel.TaskViewModel
import com.prabhakar.todo.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.fragment_add.*
import java.util.*

class AddFragment : Fragment(R.layout.fragment_add), DatePickerDialog.OnDateSetListener {
    private var date = "DD-MM-YYYY"
    private lateinit var viewModel: TaskViewModel
    lateinit var viewModelFactory: TaskViewModelFactory
    private lateinit var repo: TaskRepo
    private lateinit var taskDAO: TaskDAO
    lateinit var roomDB: TaskRoomDB
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        roomDB = TaskRoomDB.getDBObject(requireContext())
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
                startActivity(Intent(context, HomeActivity::class.java))
                activity?.supportFragmentManager?.beginTransaction()?.remove(AddFragment())


            } else {
                AlertDialog.Builder(requireActivity()).setTitle("Add Your Task")
                    .setMessage("Please Enter Your Task or Pending work")
                    .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ -> }).show()
            }
        }
    }

    private fun dateSelect() {
        DatePickerDialog(
            requireActivity(), this,
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