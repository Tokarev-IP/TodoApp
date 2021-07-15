package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.DataBaseTask

class ApiRoomRepository(appCompatActivity: AppCompatActivity) {

    private val db = DataBaseTask.getDatabase(appCompatActivity.applicationContext).taskDao()

    suspend fun insertToApiRoom(mTask: ApiEntityTaskData){
        db.insertTaskApiRoom(mTask)
    }

    fun getTasksFromApiRoom() = db.getAllTasksApiRoom()

    fun deleteFromApiRoom(mTask: ApiEntityTaskData){
        db.deleteTaskApiRoom(mTask)
    }

}