package test.app.android_school.dagger

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import test.app.android_school.room.DataBaseTask

@Module
object DaggerObjects {

    @Provides
    fun getDatabase(context: Context): DataBaseTask {
        return Room.databaseBuilder(
                context.applicationContext,
                DataBaseTask::class.java,
                "task_database"
        ).build()
    }
}