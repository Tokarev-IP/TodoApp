package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProvider
import androidx.work.*
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import test.app.android_school.R
import test.app.android_school.background.BackgroundApiWorker
import test.app.android_school.background.StartBackgroundApiWorker
import test.app.android_school.dagger.MyApplication
import test.app.android_school.fragments.RecyclerFragment
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.properties.Delegates

class MainActivity : AppCompatActivity() {

    private val TIME = "time"
    var mTime by Delegates.notNull<Int>()
    private val mBuilder = Data.Builder()

    @Inject
    lateinit var myViewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        savedInstanceState?.let {
            mTime = it.getInt(TIME)
        }
        if (savedInstanceState == null)
                mTime = 0

        (application as MyApplication)
                .myAppComponent
                .inject(this)

        val myViewModel: MyViewModel = ViewModelProvider(this, myViewModelFactory).get(MyViewModel::class.java)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame, RecyclerFragment(myViewModel))
            .commit()

    }

    override fun onResume() {
        super.onResume()

        val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()

        val workRequestStart = OneTimeWorkRequest.Builder(StartBackgroundApiWorker::class.java)
                .setConstraints(constraints)
                .addTag("START")
                .build()

        val workRequestFinish = OneTimeWorkRequest.Builder(BackgroundApiWorker::class.java)
                .setConstraints(constraints)
                .build()


        WorkManager
                .getInstance(this)
                .beginWith(workRequestStart)
                .then(workRequestFinish)
                .enqueue()


//            .enqueue(listOf(workRequestStart, workRequestFinish))
    }

    override fun onStop() {
        super.onStop()
//
//        val constraints = Constraints.Builder()
//                .setRequiredNetworkType(NetworkType.CONNECTED)
//                .build()
//
//        val workRequest: WorkRequest = OneTimeWorkRequest.Builder(BackgroundApiWorker::class.java)
//                .setConstraints(constraints)
//                .addTag("FINISH")
//                .build()
//
//        WorkManager
//            .getInstance(this)
//            .enqueue(workRequest)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(TIME, (System.currentTimeMillis()/1000L).toInt())
        super.onSaveInstanceState(outState, outPersistentState)
    }

}