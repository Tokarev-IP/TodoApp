package test.app.android_school.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Database(entities = [EntityTaskData::class, ApiEntityTaskData::class], version = 1)
abstract class DataBaseTask: RoomDatabase() {

    abstract fun taskDao(): DaoDataBase

}

