package com.prabhakar.todotaskmanager.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.prabhakar.todotaskmanager.database.model.TaskModel

@Database(entities = [TaskModel::class], version = 1)
abstract class TaskRoomDB : RoomDatabase() {

    abstract fun getDAO(): TaskDAO

    companion object {
        var instance: TaskRoomDB? = null
        fun getDBObject(context: Context): TaskRoomDB {
            if (instance != null) {
                return instance!!
            } else {
                val builder = Room.databaseBuilder(
                    context.applicationContext,
                    TaskRoomDB::class.java,
                    "TaskDB"
                )
                builder.fallbackToDestructiveMigration()
                instance = builder.build()
                return instance!!
            }
        }
    }
}