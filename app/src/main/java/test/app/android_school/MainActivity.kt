package test.app.android_school

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.addTaskActivity.AddTaskActivity
import test.app.android_school.addTaskActivity.MyViewModel
import test.app.android_school.recycler.MyRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private val CODE = 1

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myViewModel = MyViewModel()

        myViewModel.updateListOfTasks()



//        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler)
//        mRecyclerView.layoutManager = LinearLayoutManager(this)
//        mRecyclerView.adapter = mAdapter

        val floatingButton: FloatingActionButton = findViewById(R.id.floating_button)
        val mAdapter = MyRecyclerAdapter()

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        floatingButton.setOnClickListener {
            val intentActivityResult = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intentActivityResult, CODE)
        }

//        myViewModel.getListOfTasks().observe(this, Observer {
//            mAdapter.submitList(it)
//            Log.d("TAG", "observer")
//        })

    }

}