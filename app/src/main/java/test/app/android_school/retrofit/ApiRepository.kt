package test.app.android_school.retrofit

import test.app.android_school.recycler.TaskData

class ApiRepository() {

    suspend fun getTasksApi(){
        Api.apiClient.getTasksFromApi("Bearer 39828f964ef548b9beb47356380ff358")
    }

    suspend fun postTaskApi(auth: String, mTask: TaskData){
        Api.apiClient.postTaskToApi(auth, mTask)
    }
}