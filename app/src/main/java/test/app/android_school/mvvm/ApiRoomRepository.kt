package test.app.android_school.mvvm

import android.content.Context
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.DataBaseTask
import javax.inject.Inject

class ApiRoomRepository @Inject constructor(
        private val context: Context,
        db: DataBaseTask)
{

    private val dbDao = db.taskDao()

    suspend fun insertToApiRoom(mTask: ApiEntityTaskData){
        dbDao.insertTaskApiRoom(mTask)
    }

    suspend fun getTasksFromApiRoom() = dbDao.getAllTasksApiRoom()

    suspend fun deleteFromApiRoom(mTask: ApiEntityTaskData){
        dbDao.deleteTaskApiRoom(mTask)
    }

    suspend fun deleteId(mTask: ApiEntityTaskData){
        dbDao.deleteFromApiRoomWhereId(mTask.id)
    }

    suspend fun findInsert(mTask: ApiEntityTaskData): List<ApiEntityTaskData> {
        return dbDao.findInApiRoomWithIdInsert(mTask.id)
    }

    suspend fun findUpdate(mTask: ApiEntityTaskData): List<ApiEntityTaskData> {
        return dbDao.findInApiRoomWithIdUpdate(mTask.id)
    }

}