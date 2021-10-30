package com.prabhakar.todotaskmanager.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prabhakar.todotaskmanager.database.model.TaskModel

@Dao
interface TaskDao {
    @Query("select * from tasks")
    fun getAllTask(): LiveData<MutableList<TaskModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(taskModel: TaskModel)

    @Update
    fun updateTask(taskModel: TaskModel)

    @Delete
    fun deleteTask(taskModel: TaskModel)

    @Query("delete from tasks")
    fun deleteTask()
}