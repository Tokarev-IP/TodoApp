package test.app.android_school.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import test.app.android_school.recycler.TaskData

@Dao
interface DaoDataBase {

    @Query("SELECT * FROM task_data_base")
    suspend fun getAllTasks():List<TaskData>

    @Insert
    fun insertTask(task: TaskData)
}