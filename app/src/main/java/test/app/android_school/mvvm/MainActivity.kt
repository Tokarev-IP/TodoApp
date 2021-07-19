package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import test.app.android_school.R
import test.app.android_school.background.BackgroundApiWorker
import test.app.android_school.recycler.RecyclerFragment
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

//        val workRequest = PeriodicWorkRequestBuilder<BackgroundApiWorker>(
//            15, TimeUnit.MINUTES, 10, TimeUnit.MINUTES
//        )
//            .setConstraints(constraints)
//            .build()

        val workRequest: WorkRequest = OneTimeWorkRequest.Builder(BackgroundApiWorker::class.java)
            .setConstraints(constraints)
            .addTag("API")
            .build()

        WorkManager
            .getInstance(this)
            .enqueue(workRequest)


        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame, RecyclerFragment.newInstance())
            .commit()
    }

}