package test.app.android_school.addTaskActivity

import kotlinx.coroutines.*
import test.app.android_school.recycler.TaskData
import test.app.android_school.room.DataBaseTask

class MyRepository() {

    private val taskDb = DataBaseTask.getDatabase(AddTaskActivity()).taskDao()
    private val mJob = Job()

    suspend fun insertTask1(mTask: TaskData) {
        coroutineScope {
            launch(Dispatchers.IO) {
                taskDb.insertTask(mTask)
            }
        }
    }

    suspend fun insertTask(mTask: TaskData) {
            taskDb.insertTask(mTask)
    }


    suspend fun getDataAfterInsert(): List<TaskData> =
            taskDb.getAllTasks()


    fun takeTasks() {
        CoroutineScope(mJob).launch(Dispatchers.IO) {
            taskDb.getAllTasks()
        }
    }
}
