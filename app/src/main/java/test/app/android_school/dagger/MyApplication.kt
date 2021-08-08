package test.app.android_school.dagger

import android.app.Application
import android.util.Log
import androidx.work.Configuration
import androidx.work.DelegatingWorkerFactory
import test.app.android_school.background.MyWorkFactory
import test.app.android_school.background.StartWorkFactory
import javax.inject.Inject
import javax.inject.Scope

class MyApplication: Application(), Configuration.Provider {

    val myAppComponent: AppComponent by lazy {
        DaggerAppComponent
                .factory()
                .create(this)
    }

    @Inject
    lateinit var myWorkFactory: MyWorkFactory
    @Inject
    lateinit var startWorkFactory: StartWorkFactory

    override fun onCreate() {
        super.onCreate()

        this
                .myAppComponent
                .inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {

        val myWorkerFactory = DelegatingWorkerFactory()
        myWorkerFactory.addFactory(startWorkFactory)
        myWorkerFactory.addFactory(myWorkFactory)

        return Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .setWorkerFactory(myWorkerFactory)
                .build()
    }
}