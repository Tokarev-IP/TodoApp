package test.app.android_school.retrofit

import retrofit2.http.*
import test.app.android_school.room.EntityTaskData

interface ApiInterface {

    @GET("tasks")
    suspend fun getTasksFromApi(
        @Query ("api_token") authorization:String = "Bearer 39828f964ef548b9beb47356380ff358" ,
    ): List<EntityTaskData>

    @POST("tasks")
    suspend fun postTaskToApi(
        @Body task: EntityTaskData
    )

    @PUT ("tasks")
    suspend fun putTaskToApi(
        @Query ("task_id") taskId: String,
        @Body task: EntityTaskData
    )

    @DELETE ("tasks")
    suspend fun deleteTaskFromApi(
        @Query ("task_id") taskId: String,
    )

    @PUT ("tasks")
    suspend fun putAllTasksToApi(
        @Body deleted: List<EntityTaskData>
    )

}