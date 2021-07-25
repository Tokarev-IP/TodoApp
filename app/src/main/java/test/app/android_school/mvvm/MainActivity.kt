package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import test.app.android_school.R
import test.app.android_school.background.BackgroundApiWorker
import test.app.android_school.dagger.MyApplication
//import test.app.android_school.background.BackgroundApiWorker
import test.app.android_school.fragments.RecyclerFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var myViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MyApplication)
                .myAppComponent
                .inject(this)


        val myViewModel: MyViewModel = ViewModelProvider(this, myViewModelFactory).get(MyViewModel::class.java)

//        val workRequest = PeriodicWorkRequestBuilder<BackgroundApiWorker>(
//            15, TimeUnit.MINUTES, 10, TimeUnit.MINUTES
//        )
//            .setConstraints(constraints)
//            .build()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame, RecyclerFragment(myViewModel))
            .commit()
    }

    override fun onStop() {
        super.onStop()

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val workRequest: WorkRequest = OneTimeWorkRequest.Builder(BackgroundApiWorker::class.java)
                .setConstraints(constraints)
                .addTag("API")
                .build()

        WorkManager
                .getInstance(this)
                .enqueue(workRequest)
    }

}