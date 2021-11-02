package com.prabhakar.todotaskmanager.view.fragment

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
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.TaskDAO
import com.prabhakar.todotaskmanager.database.TaskRoomDB
import com.prabhakar.todotaskmanager.database.model.TaskModel
import com.prabhakar.todotaskmanager.database.repository.TaskRepo
import com.prabhakar.todotaskmanager.view.activity.HomeActivity
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModel
import com.prabhakar.todotaskmanager.viewmodel.TaskViewModelFactory
import kotlinx.android.synthetic.main.fragment_edit.*
import java.util.*


class EditFragment(val taskModel: TaskModel) : Fragment(R.layout.fragment_edit),
    DatePickerDialog.OnDateSetListener {

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
        editTask_date.setOnClickListener {
            dateSelect()
        }


        // Update Task in Database
        updateBtn.setOnClickListener {
            if (!editTask_title.text.isNullOrEmpty()
                && !editTask_desc.text.isNullOrEmpty()
                && !editTask_date.text.equals("DD-MM-YYYY")
            ) {
                taskModel.title = editTask_title.text.toString()
                taskModel.desc = editTask_desc.text.toString()
                taskModel.date = date
                viewModel.editTask(taskModel)
                activity?.supportFragmentManager?.popBackStack()

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
        editTask_date.text = date
    }
}