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
        private val mApiRoomRepository: ApiRoomRepository,
) : ViewModel() {

    private var mutListOfTasks: MutableLiveData<List<EntityTaskData>> =  MutableLiveData()
    private var doneTaskCount: MutableLiveData<Int> = MutableLiveData()
    private var time: MutableLiveData<Int> = MutableLiveData()

    fun getListOfTasks() = mutListOfTasks
    fun getDoneTaskCount() = doneTaskCount
    fun getTime() = time

    fun insertTaskToRoom(
        mTask: EntityTaskData,
        mTaskApiData: ApiEntityTaskData,
    ){
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.insertTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())
            mApiRoomRepository.insertToApiRoom(mTaskApiData)
        }
    }

    fun getAllTaskData(){
        viewModelScope.launch(Dispatchers.IO) {
            mutListOfTasks.postValue(mRepository.getAllTasksRep())

        }
    }

    fun getNotDoneTaskData(){
        viewModelScope.launch(Dispatchers.IO) {
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
    }

    fun getDoneTaskData() {
        viewModelScope.launch (Dispatchers.IO) {
            doneTaskCount.postValue(mRepository.getDoneAllTasks())
        }
    }

    fun deleteTask(mTask: EntityTaskData){
        try {
        viewModelScope.launch(Dispatchers.IO) {
            mRepository.deleteTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

        }
        }
        catch (e: Exception)
        {  }
    }

    fun updateTasks(mTask: EntityTaskData, mTaskData: ApiEntityTaskData){
        viewModelScope.launch(Dispatchers.IO) {

            mRepository.updateTask(mTask)
            mutListOfTasks.postValue(mRepository.getNotDoneAllTasks())

            if (mApiRoomRepository.findInsert(mTaskData).isEmpty()
                && mApiRoomRepository.findUpdate(mTaskData).isEmpty())
                    mApiRoomRepository.insertToApiRoom(mTaskData)
            else if (mApiRoomRepository.findInsert(mTaskData).isNotEmpty()){
                mApiRoomRepository.deleteId(mTaskData)
                mApiRoomRepository.insertToApiRoom(
                    ApiEntityTaskData(
                        mTaskData.id,
                        mTaskData.text,
                        mTaskData.importance,
                        mTaskData.done,
                        mTaskData.deadline,
                        mTaskData.createdAt,
                        mTaskData.updatedAt,
                        "insert"
                    )
                )
            }
            else if (mApiRoomRepository.findUpdate(mTaskData).isNotEmpty()){
                mApiRoomRepository.deleteId(mTaskData)
                mApiRoomRepository.insertToApiRoom(mTaskData)
            }

        }
    }

    fun addToApiRoomTask(mTaskData: ApiEntityTaskData){
        viewModelScope.launch(Dispatchers.IO) {

            mApiRoomRepository.insertToApiRoom(mTaskData)
        }
    }

    fun setTime(mTime: Int){
        viewModelScope.launch(Dispatchers.IO) {
            time.postValue(mTime)
        }
    }

}