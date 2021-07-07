package test.app.android_school.recycler

import java.util.*
import java.util.concurrent.TimeUnit

data class TaskData(
        val text: String,
        val importance: String,
        val done: Boolean,
        val deadline: Date?,
        val created_at: Date,
        val updated_at: Date,
) {
}