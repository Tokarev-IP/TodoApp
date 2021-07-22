package test.app.android_school.dagger

import android.app.Application

class MyApplication: Application() {

    val myAppComponent: AppComponent by lazy {
        DaggerAppComponent
                .factory()
                .create(this)
    }


}