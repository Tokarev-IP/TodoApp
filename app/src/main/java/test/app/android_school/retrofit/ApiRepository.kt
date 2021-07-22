package test.app.android_school.retrofit

import test.app.android_school.recycler.TaskData

class ApiRepository() {

    private val api = Api.apiClient

    private val apiKey: String = "Bearer 39828f964ef548b9beb47356380ff358"

    suspend fun getTasksApi() {
        api.getTasksFromApi(apiKey)
    }

    fun postTaskApi(mTask: TaskData){
        api.postTaskToApi(apiKey, mTask)
    }

    fun putTaskApi(id: String, mTask: TaskData){
        api.putTaskToApi(id, apiKey, mTask)
    }

    fun deleteTaskApi (id: String){
        api.deleteTaskFromApi(id, apiKey)
    }
}