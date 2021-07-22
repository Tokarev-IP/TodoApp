package test.app.android_school.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import test.app.android_school.recycler.TaskData
import test.app.android_school.room.ApiEntityTaskData

import test.app.android_school.room.EntityTaskData
import javax.inject.Inject

class MyViewModel @Inject constructor(
        private val mRepository: MyRepository,
) : ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<EntityTaskData>> =  MutableLiveData()
    private var doneTaskCount: MutableLiveData<Int> = MutableLiveData()

    fun getListOfTasks() = mutListOfTasks
    fun getDoneTaskCount() = doneTaskCount

    fun updateListOfTasks(
        mTask: EntityTaskData,
        mTaskData: TaskData,
        mTaskApiData: ApiEntityTaskData,
    ){
//        mApiRoomRepository = ApiRoomRepository(appCompatActivity)
//        mRepository = MyRepository(appCompatActivity)

        viewModelScope.launch(Dispatchers.IO) {

            mRepository.insertTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

//            mApiRoomRepository.insertToApiRoom(mTaskApiData)
        }

    }

    fun getAllTaskData(){
//        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mutListOfTasks.postValue(mRepository.getAllTasksRep())

        }
    }

    fun getNotDoneTaskData(){
//        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun getDoneTaskData() {
//        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch (Dispatchers.IO) {

            doneTaskCount.postValue(mRepository.getDoneAllTasks())
        }
    }

    fun makeIsDone(mTask: EntityTaskData){
//        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mRepository.makeDone(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun deleteTask(mTask: EntityTaskData, mTaskData: TaskData){
//        mRepository = MyRepository(appCompatActivity)
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

    fun updateTasks(mTask: EntityTaskData){
//        mRepository = MyRepository(appCompatActivity)
        viewModelScope.launch(Dispatchers.IO) {

            mRepository.updateTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

}