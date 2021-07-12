package test.app.android_school.retrofit

import retrofit2.Call
import retrofit2.http.*
import test.app.android_school.recycler.TaskData
import test.app.android_school.room.EntityTaskData

interface ApiInterface {

    @GET("tasks")
    suspend fun getTasksFromApi(
            @Header("Authorization") auth: String,
    ): List<TaskData>

    @POST("tasks")
    suspend fun postTaskToApi(
            @Header ("Authorization") auth: String,
            @Body task: TaskData
    ): Call<TaskData>

    @PUT ("tasks")
    suspend fun putTaskToApi(
            @Path ("task_id") taskId: String,
            @Body task: EntityTaskData
    ): Call<EntityTaskData>

    @DELETE ("tasks")
    suspend fun deleteTaskFromApi(
            @Query ("task_id") taskId: String,
    ): Call<EntityTaskData>

    @PUT ("tasks")
    suspend fun putAllTasksToApi(
            @Body deleted: List<EntityTaskData>
    ): Call<EntityTaskData>

}