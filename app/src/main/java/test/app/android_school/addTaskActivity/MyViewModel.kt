package test.app.android_school.addTaskActivity

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.android_school.room.EntityTaskData

class MyViewModel() : ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<EntityTaskData>> =  MutableLiveData()

    fun getListOfTasks() = mutListOfTasks

    fun updateListOfTasks(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mRepository.insertTask(mTask)
            mutListOfTasks.postValue(mRepository.getAllTasks())

            Log.d("TAG", mutListOfTasks.toString())
        }
    }

    fun getAllTaskData(appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mutListOfTasks.value = mRepository.getAllTasks()

            Log.d("TAG", mutListOfTasks.toString())
        }
    }

}