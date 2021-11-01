package com.prabhakar.todotaskmanager.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.prabhakar.todotaskmanager.R
import com.prabhakar.todotaskmanager.database.model.TaskModel
import kotlinx.android.synthetic.main.activity_edit.*

class EditActivity(val taskModel: TaskModel) : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)
        editTask_title.text=taskModel.title.toString() as Editable
    }
}