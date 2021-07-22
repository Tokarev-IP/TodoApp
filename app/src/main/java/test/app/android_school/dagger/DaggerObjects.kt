package test.app.android_school.dagger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Module
import dagger.Provides
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.room.DataBaseTask

@Module
class DaggerObjects {

    @Provides
    fun getMyViewModel(context: Context): MyViewModel {
        return ViewModelProvider(context as AppCompatActivity).get(MyViewModel::class.java)
    }

    @Provides
    fun getDatabase(context: Context): DataBaseTask {
        return Room.databaseBuilder(
                context.applicationContext,
                DataBaseTask::class.java,
                "task_database"
        ).build()
    }
}