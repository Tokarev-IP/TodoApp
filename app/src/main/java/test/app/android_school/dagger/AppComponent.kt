package test.app.android_school.dagger

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.BindsInstance
import dagger.Component
import test.app.android_school.addTaskFragment.AddTaskFragment
import test.app.android_school.mvvm.MainActivity
import test.app.android_school.recycler.RecyclerFragment
import test.app.android_school.viewModel.ViewModelModule

@Component (modules = [
    DaggerObjects::class,
    ViewModelModule::class
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