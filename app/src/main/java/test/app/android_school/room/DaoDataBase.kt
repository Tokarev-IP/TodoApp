package test.app.android_school.room

import androidx.room.*

@Dao
interface DaoDataBase {

    @Query("SELECT * FROM task_DataBase")
    suspend fun getAllTasks():List<EntityTaskData>

    @Query("SELECT * FROM task_DataBase WHERE done = 'false' ")
    suspend fun getNoCompleteTasks():List<EntityTaskData>

    @Update
    suspend fun makeTaskIsDone(mTask: EntityTaskData)

    @Delete
    suspend fun deleteTask(mTask: EntityTaskData)

    @Insert
    suspend fun insertTask(mTask: EntityTaskData)
}