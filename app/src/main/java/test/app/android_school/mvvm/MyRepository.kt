package test.app.android_school.mvvm

import test.app.android_school.dagger.ApplicationScope
import test.app.android_school.room.DataBaseTask
import test.app.android_school.room.EntityTaskData
import javax.inject.Inject

@ApplicationScope
class MyRepository @Inject constructor(db: DataBaseTask) {

    private val dbDao = db.taskDao()

    suspend fun insertTask(mTask: EntityTaskData) {
        dbDao.insertTask(mTask)
    }

    suspend fun getAllTasksRep(): List<EntityTaskData> {
       return dbDao.getAllTasks()
    }

    suspend fun getNotDoneAllTasks(): List<EntityTaskData> {
        return dbDao.getNoCompleteTasks()
    }

    suspend fun getDoneAllTasks(): Int {
        return dbDao.getCountCompleteTasks()
    }

    suspend fun deleteTask(mTask: EntityTaskData){
        dbDao.deleteTask(mTask)
    }

    suspend fun updateTask(mTask: EntityTaskData) {
        dbDao.updateTask(mTask)
    }

    suspend fun findTask(mId: String) =
            dbDao.findTask(mId)
}
