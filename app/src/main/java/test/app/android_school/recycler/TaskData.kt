package test.app.android_school.recycler

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