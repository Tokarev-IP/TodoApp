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
import test.app.android_school.room.ApiEntityTaskData

import test.app.android_school.room.EntityTaskData

class MyViewModel() : ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<EntityTaskData>> =  MutableLiveData()
    private var doneTaskCount: MutableLiveData<Int> = MutableLiveData()
    private val apiRep = ApiRepository()
    lateinit var mRepository: MyRepository
    lateinit var mApiRoomRepository: ApiRoomRepository

    fun getListOfTasks() = mutListOfTasks

    fun getDoneTaskCount() = doneTaskCount

    fun updateListOfTasks(
        mTask: EntityTaskData,
        mTaskData: TaskData,
        mTaskApiData: ApiEntityTaskData,
        appCompatActivity: AppCompatActivity
    ){
        mApiRoomRepository = ApiRoomRepository(appCompatActivity)
        mRepository = MyRepository(appCompatActivity)

        viewModelScope.launch(Dispatchers.IO) {

            mRepository.insertTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

            mApiRoomRepository.insertToApiRoom(mTaskApiData)
        }

    }

    fun getAllTaskData(appCompatActivity: AppCompatActivity){
        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mutListOfTasks.postValue(mRepository.getAllTasksRep())

        }
    }

    fun getNotDoneTaskData(appCompatActivity: AppCompatActivity){
        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun getDoneTaskData(appCompatActivity: AppCompatActivity) {
        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch (Dispatchers.IO) {

            doneTaskCount.postValue(mRepository.getDoneAllTasks())
        }
    }

    fun makeIsDone(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mRepository.makeDone(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun deleteTask(mTask: EntityTaskData, mTaskData: TaskData, appCompatActivity: AppCompatActivity){
        mRepository = MyRepository(appCompatActivity)
        try {

        viewModelScope.launch(Dispatchers.IO) {

            mRepository.deleteTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }

            viewModelScope.launch(Dispatchers.IO) {
//                apiRep.deleteTaskApi(mTask.id)
            }
            Log.d("TASKID", mTaskData.id)
        }
        catch (e: Exception)
        {  }
    }

    fun updateTasks(mTask: EntityTaskData, appCompatActivity: AppCompatActivity){
        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mRepository.updateTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

}