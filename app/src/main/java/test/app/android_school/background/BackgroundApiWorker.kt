package test.app.android_school.background

import android.content.Context
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
//import test.app.android_school.mvvm.ApiRoomRepository
import test.app.android_school.recycler.TaskData
import test.app.android_school.retrofit.ApiRepository
import java.lang.Exception
import java.util.concurrent.TimeUnit

//class BackgroundApiWorker(private val appContext: Context, workerParams: WorkerParameters):
//        Worker(appContext, workerParams) {
//
////    private val apiRoomRep = ApiRoomRepository(appContext as AppCompatActivity)
//    private val apiRep = ApiRepository()
//
//    override fun doWork(): Result {
//
////        for (i in 0..40){
////            Log.d("FOR", i.toString())
////            TimeUnit.MILLISECONDS.sleep(250)
////        }
//
//        try {
//            val list = ApiRoomRepository(appContext as AppCompatActivity).getTasksFromApiRoom()
//            val count = list.size
//            Log.d("COUNT", count.toString())
//
////            for (i in 0 until count){
////                when(list[i].actionApi){
////                    "insert"->{
////                        apiRep.postTaskApi(
////                            TaskData(
////                                list[i].id,
////                                list[i].text,
////                                list[i].importance,
////                                list[i].done,
////                                list[i].deadline,
////                                list[i].createdAt,
////                                list[i].updatedAt
////                            )
////                        )
////                        Log.d("WORKMANAGER", "insert")
////                    }
////                    "update"->{
////                        apiRep.putTaskApi(
////                            list[i].id,
////                            TaskData(
////                                list[i].id,
////                                list[i].text,
////                                list[i].importance,
////                                list[i].done,
////                                list[i].deadline,
////                                list[i].createdAt,
////                                list[i].updatedAt
////                            )
////                        )
////                    }
////                    "delete"->{
////                        apiRep.deleteTaskApi(list[i].id)
////                    }
////                }
////                TimeUnit.SECONDS.sleep(5)
////                ApiRoomRepository(appContext as AppCompatActivity).deleteFromApiRoom(list[i])
////            }
//
//        } catch (e: Exception) {
//            Log.d("BACKERROR", "exeption")
//            return Result.failure()
//        }
//
//        return Result.success()
//    }
//
//}