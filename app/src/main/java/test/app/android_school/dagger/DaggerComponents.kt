package test.app.android_school.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import test.app.android_school.addTaskFragment.AddTaskFragment
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.recycler.RecyclerFragment

@Component (modules = [
    MyViewModel::class,
])

interface DaggerComponents {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context): DaggerComponents
    }

    fun inject(fragment: AddTaskFragment)
    fun inject(fragment: RecyclerFragment)
}