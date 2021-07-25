package test.app.android_school.background

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import test.app.android_school.mvvm.ApiRoomRepository
//import test.app.android_school.mvvm.ApiRoomRepository
import test.app.android_school.recycler.TaskData
import test.app.android_school.retrofit.ApiRepository
import java.lang.Exception
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BackgroundApiWorker (
        appContext: Context,
        workerParams: WorkerParameters,
        private val apiRoomRep: ApiRoomRepository,
        private val apiRep: ApiRepository,
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO){

        try {
            val list = apiRoomRep.getTasksFromApiRoom()
            val count = list.size

            if (count > 0) {
                Log.d("COUNT", count.toString())

                for (i in 0 until count) {
                    when (list[i].actionApi) {
                        "insert" -> {
                            apiRep.postTaskApi(
                                    TaskData(
                                            list[i].id,
                                            list[i].text,
                                            list[i].importance,
                                            list[i].done,
                                            list[i].deadline,
                                            list[i].createdAt,
                                            list[i].updatedAt
                                    )
                            )
                            Log.d("WORKMANAGER", "insert")
                        }
                        "update" -> {
                            apiRep.putTaskApi(
                                    list[i].id,
                                    TaskData(
                                            list[i].id,
                                            list[i].text,
                                            list[i].importance,
                                            list[i].done,
                                            list[i].deadline,
                                            list[i].createdAt,
                                            list[i].updatedAt
                                    )
                            )
                        }
                        "delete" -> {
                            apiRep.deleteTaskApi(list[i].id)
                        }
                    }
                    apiRoomRep.deleteFromApiRoom(list[i])
                }
            }

        } catch (e: Exception) {
            Log.d("BACKERROR", e.toString())
            Result.failure()
        }

        Result.success()
    }

}