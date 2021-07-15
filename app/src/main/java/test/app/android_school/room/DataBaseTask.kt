package test.app.android_school.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EntityTaskData::class, ApiEntityTaskData::class], version = 1)
abstract class DataBaseTask : RoomDatabase() {

    abstract fun taskDao(): DaoDataBase

    companion object {
        @Volatile
        private var INSTANCE: DataBaseTask? = null

        fun getDatabase(context: Context): DataBaseTask {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        DataBaseTask::class.java,
                        "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}