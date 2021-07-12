package test.app.android_school.mvvm

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.android_school.recycler.TaskData
import test.app.android_school.retrofit.ApiRepository

import test.app.android_school.room.EntityTaskData

class MyViewModel() : ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<EntityTaskData>> =  MutableLiveData()
    private var doneTaskCount: MutableLiveData<Int> = MutableLiveData()
    private val apiRep = ApiRepository()

    fun getListOfTasks() = mutListOfTasks

    fun getDoneTaskCount() = doneTaskCount

    private val vMScope = viewModelScope
    private val viewMScope = viewModelScope

    fun updateListOfTasks(mTask: EntityTaskData, mTaskData: TaskData, appCompatActivity: AppCompatActivity){
        val mRepository = MyRepository(appCompatActivity)
//        viewMScope.launch(Dispatchers.Main) {
//
//            mRepository.insertTask(mTask)
//            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())
//
//        }
        try {

            viewModelScope.launch() {
                Log.d("TAG",
                        apiRep.getTasksApi()
                                .toString()
                )
            }

//            vMScope.launch(Dispatchers.IO) {
//                apiRep.postTaskApi(
//                        "Bearer 39828f964ef548b9beb47356380ff358",
//                        mTaskData)
//            }
        }
        catch (e: Exception)
        {
            Log.d("TAG", e.toString())}

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