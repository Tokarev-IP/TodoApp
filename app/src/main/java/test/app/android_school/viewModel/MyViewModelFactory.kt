package test.app.android_school.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import test.app.android_school.mvvm.MyViewModel
import javax.inject.Inject
import javax.inject.Provider


class MyViewModelFactory: ViewModelProvider.Factory {

    private var mMyViewModelProvider: Provider<MyViewModel>? = null

    @Inject
    fun viewModelFactory(myViewModelProvider: Provider<MyViewModel>?) {
        mMyViewModelProvider = myViewModelProvider
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var viewModel: ViewModel? = null
        if (modelClass == MyViewModel::class){
            mMyViewModelProvider?.let {
                viewModel = it.get() }
        }
        else throw RuntimeException("unsupported view model class: " + modelClass);

        return viewModel as T
    }
}