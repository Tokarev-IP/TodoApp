package test.app.android_school.recycler

import java.util.*

data class TaskData(
        val date: Date?,
        val task: String,
        val isComplete: Boolean,
        val priority: Int,
) {
}