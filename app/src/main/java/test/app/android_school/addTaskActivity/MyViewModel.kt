package test.app.android_school.addTaskActivity

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.app.android_school.recycler.TaskData
import androidx.lifecycle.viewModelScope

class MyViewModel(): ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<TaskData>> =  MutableLiveData()

    private val mRepository = MyRepository()

    fun getListOfTasks() = mutListOfTasks

    fun updateListOfTasks(){
//        viewModelScope.launch {
//            mRepository.insertTask(mTask)
//        }
        Log.d("TAG", "VIEMODEL")
    }

}