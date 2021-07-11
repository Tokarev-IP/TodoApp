package test.app.android_school.retrofit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import test.app.android_school.room.EntityTaskData

class ApiRepository {

    suspend fun postTaskApi(mTask: EntityTaskData){
            Api.apiClient.postTaskToApi(mTask)
    }
}