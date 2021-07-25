package test.app.android_school.background

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import test.app.android_school.mvvm.ApiRoomRepository
import test.app.android_school.retrofit.ApiRepository
import javax.inject.Inject

class MyWorkFactory @Inject constructor(
        private val apiRoomRep: ApiRoomRepository,
        private val apiRep: ApiRepository,
): WorkerFactory() {

    override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
    ): ListenableWorker {
        return BackgroundApiWorker(appContext, workerParameters, apiRoomRep, apiRep)
    }
}