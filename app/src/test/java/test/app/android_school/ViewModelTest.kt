package test.app.android_school

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.spy
import com.nhaarman.mockito_kotlin.verify
import junit.framework.TestCase.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import test.app.android_school.mvvm.ApiRoomRepository
import test.app.android_school.mvvm.MyRepository
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.EntityTaskData

class ViewModelTest(){

    @Mock
    private lateinit var viewModel: MyViewModel

    @Mock
    private lateinit var isLoadingLiveData: LiveData<Boolean>

    @Mock
    private lateinit var observer: Observer<Boolean>

    @Mock
    private lateinit var myRep: MyRepository

    @Mock
    private lateinit var apiMyRep: ApiRoomRepository

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        viewModel = spy(
            MyViewModel(
                myRep,
                apiMyRep,
            )
        )
        isLoadingLiveData = viewModel.dataLoading
    }

    @Test
    fun `verify liveData getAllTasks`() {
        viewModel.dataLoading.observeForever(observer)
        viewModel.getAllTaskData()
        verify(observer).onChanged(true)
        verify(observer).onChanged(false)
        viewModel.dataLoading.removeObserver(observer)
    }

    @Test
    fun `verify insert to room`(){
        viewModel.dataLoading.observeForever(observer)
        viewModel.insertTaskToRoom(
            EntityTaskData(
                "test id",
                "test",
                "low",
                false,
                500,
                500,
                500,
            ),
            ApiEntityTaskData(
                "test id",
                "test",
                "low",
                false,
                500,
                500,
                500,
                "insert"
            )
        )
        verify(observer).onChanged(true)
        verify(observer).onChanged(false)
        viewModel.dataLoading.removeObserver(observer)
    }

    @Test
    fun `verify delete from room`(){
        viewModel.dataLoading.observeForever(observer)
        viewModel.deleteTask(
            EntityTaskData(
                "test id",
                "test",
                "low",
                false,
                500,
                500,
                500,
            )
        )
        verify(observer).onChanged(true)
        verify(observer).onChanged(false)
        viewModel.dataLoading.removeObserver(observer)
    }

    @Test
    fun `verify insert to api room`(){
        
        var isLoading = isLoadingLiveData.value

        isLoading?.let { assert(it) }

        viewModel.addToApiRoomTask(
            ApiEntityTaskData(
                "test id",
                "test",
                "low",
                false,
                500,
                500,
                500,
                "insert"
            )
        )

        isLoading = isLoadingLiveData.value
    }



}
