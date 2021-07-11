package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.android_school.retrofit.Api
import test.app.android_school.retrofit.ApiRepository
import test.app.android_school.room.EntityTaskData

class MyViewModel() : ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<EntityTaskData>> =  MutableLiveData()
    private var doneTaskCount: MutableLiveData<Int> = MutableLiveData()

    fun getListOfTasks() = mutListOfTasks

    fun getDoneTaskCount() = doneTaskCount

    fun updateListOfTasks(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mRepository.insertTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }

    }

    fun getAllTaskData(appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mutListOfTasks.value = mRepository.getAllTasks()

        }
    }

    fun getNotDoneTaskData(appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mutListOfTasks.value = mRepository.getNotDoneAllTasks()

        }
    }

    fun getDoneTaskData(appCompatActivity: AppCompatActivity) {
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch (Dispatchers.Main) {

            doneTaskCount.value = mRepository.getDoneAllTasks()
        }
    }

    fun makeIsDone(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mRepository.makeDone(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun deleteTask(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mRepository.deleteTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun updateTasks(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.Main) {

            mRepository.updateTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

}