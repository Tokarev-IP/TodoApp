package test.app.android_school.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.concurrent.TimeUnit

@Entity (tableName = "task_data_base")
data class RoomDataBase(

    @PrimaryKey (autoGenerate = true)
    val id: String,

    @ColumnInfo
    val text: String,

    @ColumnInfo
    val importance: String,

    @ColumnInfo
    val done: Boolean,

    @ColumnInfo
    val deadline: TimeUnit,

    @ColumnInfo
    val created_at: TimeUnit,

    @ColumnInfo
    val updated_at: TimeUnit,

) {
}