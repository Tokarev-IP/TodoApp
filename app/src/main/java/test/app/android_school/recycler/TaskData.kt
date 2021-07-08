package test.app.android_school.recycler

import java.util.*
import java.util.concurrent.TimeUnit

data class TaskData(
        val id: String,
        val text: String,
        val importance: String,
        val done: Boolean,
        val deadline: Long?,
        val created_at: Long,
        val updated_at: Long,
) {
}