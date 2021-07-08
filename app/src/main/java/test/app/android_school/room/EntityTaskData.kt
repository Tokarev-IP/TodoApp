package test.app.android_school.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

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

    @ColumnInfo (name = "deadLine")
    val deadline: Long?,

    @ColumnInfo (name = "created_at")
    val createdAt: Long,

    @ColumnInfo (name = "updated_at")
    val updatedAt: Long,
    ) {
}