package test.app.android_school.dagger

import android.app.Application
import androidx.appcompat.app.AppCompatActivity

class MyApplication: Application() {

    val myAppComponent: AppComponent by lazy {
        DaggerAppComponent
                .factory()
                .create(this)
    }
//    val daga = DaggerObjects().getMyViewModel(this)

}