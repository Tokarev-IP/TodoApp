package test.app.android_school.dagger

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import test.app.android_school.addTaskFragment.AddTaskFragment
import test.app.android_school.mvvm.MainActivity
import test.app.android_school.recycler.RecyclerFragment

@Component (modules = [
    DaggerObjects::class,
])

interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: AddTaskFragment)
    fun inject(fragment: RecyclerFragment)
    fun inject(activity: MainActivity)
}