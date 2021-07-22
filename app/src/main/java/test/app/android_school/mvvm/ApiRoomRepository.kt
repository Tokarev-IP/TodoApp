package test.app.android_school.mvvm

import android.content.Context
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.DataBaseTask
import javax.inject.Inject

class ApiRoomRepository @Inject constructor(
        private val context: Context,
        private val db: DataBaseTask)
{

    private val dbDao = db.taskDao()

    suspend fun insertToApiRoom(mTask: ApiEntityTaskData){
        dbDao.insertTaskApiRoom(mTask)
    }

    fun getTasksFromApiRoom() = dbDao.getAllTasksApiRoom()

    fun deleteFromApiRoom(mTask: ApiEntityTaskData){
        dbDao.deleteTaskApiRoom(mTask)
    }

}