package test.app.android_school.background

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import test.app.android_school.mvvm.MyRepository
import test.app.android_school.retrofit.ApiRepository
import javax.inject.Inject

class StartWorkFactory @Inject constructor(
        private val apiRep: ApiRepository,
        private val myRep: MyRepository,
): WorkerFactory() {

    override fun createWorker(
            appContext: Context,
            workerClassName: String,
            workerParameters: WorkerParameters
    ): ListenableWorker {
        return StartBackgroundApiWorker(appContext, workerParameters, apiRep, myRep)
    }
}