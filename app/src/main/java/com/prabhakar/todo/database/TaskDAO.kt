package com.prabhakar.todo.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prabhakar.todo.database.model.TaskModel

@Dao
interface TaskDAO {
    @Query("select * from tasks")
    fun getAllTask(): LiveData<MutableList<TaskModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(taskModel: TaskModel)

    @Update
    fun updateTask(taskModel: TaskModel)

    @Delete
    fun deleteTask(taskModel: TaskModel)

    @Query("delete from tasks")
    fun deleteAllTask()
}