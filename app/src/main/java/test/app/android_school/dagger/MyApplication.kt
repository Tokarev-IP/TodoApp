package test.app.android_school.dagger

import android.app.Application
import androidx.work.Configuration
import test.app.android_school.background.MyWorkFactory
import javax.inject.Inject

class MyApplication: Application(), Configuration.Provider {

    val myAppComponent: AppComponent by lazy {
        DaggerAppComponent
                .factory()
                .create(this)
    }

    @Inject
    lateinit var myWorkFactory: MyWorkFactory

    override fun onCreate() {
        super.onCreate()

        this
                .myAppComponent
                .inject(this)
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
                .setMinimumLoggingLevel(android.util.Log.DEBUG)
                .setWorkerFactory(myWorkFactory)
                .build()
    }

}