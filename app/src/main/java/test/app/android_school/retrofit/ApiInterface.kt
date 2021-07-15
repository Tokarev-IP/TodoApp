package test.app.android_school.retrofit

import retrofit2.http.*
import test.app.android_school.recycler.TaskData
import test.app.android_school.room.EntityTaskData

interface ApiInterface {

    @GET("tasks")
    suspend fun getTasksFromApi(
        @Header("Authorization") auth: String,
    ): List<TaskData>

    @POST("tasks")
    fun postTaskToApi(
        @Header ("Authorization") auth: String,
        @Body task: TaskData
    ): TaskData

    @PUT ("tasks/{task_id}")
    fun putTaskToApi(
        @Path ("task_id") taskId: String,
        @Header ("Authorization") auth: String,
        @Body task: TaskData
    ): TaskData

    @DELETE("tasks/{task_id}")
    fun deleteTaskFromApi(
        @Path ("task_id") taskId: String,
        @Header ("Authorization") auth: String,
    ): TaskData

}