package test.app.android_school.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "task_DataBase")
data class EntityTaskData(

    @PrimaryKey
    @ColumnInfo (name = "id")
    val id: String,

    @ColumnInfo (name = "text")
    val text: String,

    @ColumnInfo (name = "importance")
    val importance: String,

    @ColumnInfo (name = "done")
    val done: Boolean,

    @ColumnInfo (name = "deadline")
    val deadline: Int,

    @ColumnInfo (name = "created_at")
    val createdAt: Int,

    @ColumnInfo (name = "updated_at")
    val updatedAt: Int,
    ) {
}