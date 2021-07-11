package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.room.DataBaseTask
import test.app.android_school.room.EntityTaskData

class MyRepository(appCompatActivity: AppCompatActivity) {

    private val db = DataBaseTask.getDatabase(appCompatActivity.applicationContext).taskDao()

    suspend fun insertTask(mTask: EntityTaskData) {
        db.insertTask(mTask)
    }

    suspend fun getAllTasks(): List<EntityTaskData> {
       return db.getAllTasks()
    }

    suspend fun getNotDoneAllTasks(): List<EntityTaskData> {
        return db.getNoCompleteTasks()
    }

    suspend fun getDoneAllTasks(): Int {
        return db.getCountCompleteTasks()
    }

    suspend fun makeDone(mTask: EntityTaskData){
        db.makeTaskIsDone(mTask)
    }

    suspend fun deleteTask(mTask: EntityTaskData){
        db.deleteTask(mTask)
    }

    suspend fun updateTask(mTask: EntityTaskData) {
        db.updateTask(mTask)
    }

}
