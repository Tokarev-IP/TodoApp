package test.app.android_school.retrofit

import test.app.android_school.BuildConfig
import test.app.android_school.dagger.ApplicationScope
import test.app.android_school.recycler.TaskData
import javax.inject.Inject

@ApplicationScope
class ApiRepository @Inject constructor(private val api: ApiInterface) {

    private val apiKey: String = BuildConfig.API_KEY

    suspend fun getTasksApi(): List<TaskData> {
        return api.getTasksFromApi(apiKey)
    }

    suspend fun postTaskApi(mTask: TaskData){
        api.postTaskToApi(apiKey, mTask)
    }

    suspend fun putTaskApi(id: String, mTask: TaskData){
        api.putTaskToApi(id, apiKey, mTask)
    }

    suspend fun deleteTaskApi (id: String){
        api.deleteTaskFromApi(id, apiKey)
    }
}