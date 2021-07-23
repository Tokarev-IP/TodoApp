package test.app.android_school.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [EntityTaskData::class, ApiEntityTaskData::class], version = 1)
abstract class DataBaseTask: RoomDatabase() {

    abstract fun taskDao(): DaoDataBase


}

