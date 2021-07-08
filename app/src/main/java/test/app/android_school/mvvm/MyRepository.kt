package test.app.android_school.mvvm

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.room.DataBaseTask
import test.app.android_school.room.EntityTaskData

class MyRepository(appCompatActivity: AppCompatActivity) {

    private val db = DataBaseTask.getDatabase(appCompatActivity.applicationContext).taskDao()

    suspend fun insertTask(mTask: EntityTaskData) {
        db.insertTask(mTask)
        Log.d("TAG", "Insert")
    }

    suspend fun getAllTasks(): List<EntityTaskData> {
        Log.d("TAG", "Get all")
       return db.getAllTasks()
    }

    suspend fun makeDone(mTask: EntityTaskData){
        db.makeTaskIsDone(mTask)
    }

    suspend fun deleteTask(mTask: EntityTaskData){
        db.deleteTask(mTask)
    }

}
