package test.app.android_school

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.app.android_school.recycler.TaskData

class MyViewModel: ViewModel() {

    private var mutListOfTasks: MutableLiveData<ArrayList<TaskData>> =  MutableLiveData()

    fun getListOfTasks() = mutListOfTasks

    fun updateListOfTasks(taskData: TaskData, taskList: ArrayList<TaskData>){
        mutListOfTasks.value?.add(taskData)
        Log.d("TAG", mutListOfTasks.value?.get(0)?.task.toString())
        mutListOfTasks.value = taskList
        Log.d("TAG", "update")
        Log.d("TAG", mutListOfTasks.value?.get(0)?.task.toString())
    }


}