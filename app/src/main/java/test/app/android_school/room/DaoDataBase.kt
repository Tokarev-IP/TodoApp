package test.app.android_school.room

import androidx.room.*

@Dao
interface DaoDataBase {

    @Query("SELECT * FROM task_DataBase")
    suspend fun getAllTasks():List<EntityTaskData>

    @Query("SELECT * FROM task_DataBase WHERE done = '0' ")
    suspend fun getNoCompleteTasks():List<EntityTaskData>

    @Query("SELECT COUNT(*) FROM task_DataBase WHERE done = '1' ")
    suspend fun getCountCompleteTasks(): Int

    @Query ("SELECT * from task_DataBase WHERE id = :mId")
    suspend fun findTask(mId: String): List<EntityTaskData>

    @Update
    suspend fun makeTaskIsDone(mTask: EntityTaskData)

    @Delete
    suspend fun deleteTask(mTask: EntityTaskData)

    @Insert
    suspend fun insertTask(mTask: EntityTaskData)

    @Update
    suspend fun updateTask(mTask: EntityTaskData)



    @Query("SELECT * from action_api_task_DataBase")
    suspend fun getAllTasksApiRoom(): List<ApiEntityTaskData>

    @Insert
    suspend fun insertTaskApiRoom(mTask: ApiEntityTaskData)

    @Query ("DELETE from action_api_task_DataBase WHERE id = :mId")
    suspend fun deleteFromApiRoomWhereId(mId: String)

    @Delete
    suspend fun deleteTaskApiRoom(mTask: ApiEntityTaskData)

    @Query ("SELECT * from action_api_task_DataBase WHERE id = :mId AND  action_api ='insert'")
    suspend fun findInApiRoomWithIdInsert(mId: String): List<ApiEntityTaskData>

    @Query ("SELECT * from action_api_task_DataBase WHERE id = :mId AND action_api ='update'")
    suspend fun findInApiRoomWithIdUpdate(mId: String): List<ApiEntityTaskData>

}