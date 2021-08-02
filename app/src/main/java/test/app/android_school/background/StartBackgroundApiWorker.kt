package test.app.android_school.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import test.app.android_school.mvvm.MyRepository
import test.app.android_school.retrofit.ApiRepository
import test.app.android_school.room.EntityTaskData

class StartBackgroundApiWorker(
    appContext: Context,
    workerParams: WorkerParameters,
    private val apiRep: ApiRepository,
    private val myRep: MyRepository,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {


        try {
            Log.d("APIDATA", "help")

            val apiRoomList = apiRep.getTasksApi()
            Log.d("APIDATA", apiRoomList.toString())

            val count = apiRoomList.size

            for (i in 0 until count) {
                if (apiRoomList[i].updatedAt > 0) {
                    if (myRep.findTask(apiRoomList[i].id).isNotEmpty()) {
                        myRep.updateTask(
                            EntityTaskData(
                                apiRoomList[i].id,
                                apiRoomList[i].text,
                                apiRoomList[i].importance,
                                apiRoomList[i].done,
                                apiRoomList[i].deadline,
                                apiRoomList[i].createdAt,
                                apiRoomList[i].updatedAt,
                            )
                        )
                    } else myRep.insertTask(
                        EntityTaskData(
                            apiRoomList[i].id,
                            apiRoomList[i].text,
                            apiRoomList[i].importance,
                            apiRoomList[i].done,
                            apiRoomList[i].deadline,
                            apiRoomList[i].createdAt,
                            apiRoomList[i].updatedAt,
                        )
                    )
                }
            }
        } catch (e: Exception) {
            Log.d("ERROR", e.toString())
            Result.failure()
        }

        Result.success()
    }

}