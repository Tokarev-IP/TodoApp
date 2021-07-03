package test.app.android_school

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.addTaskActivity.AddTaskActivity
import test.app.android_school.recycler.MyRecyclerAdapter
import test.app.android_school.recycler.TaskData

class MainActivity : AppCompatActivity() {

    private val CODE = 1
    private val mAdapter = MyRecyclerAdapter()
    private val mViewModel = MyViewModel()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter

        val floatingButton: FloatingActionButton = findViewById(R.id.floating_button)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        floatingButton.setOnClickListener {
            val intentActivityResult = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intentActivityResult, CODE)
        }

        mViewModel.getListOfTasks().observe(this, Observer {
            mAdapter.updateAdapter(it)
            Log.d("TAG", "observer")
        })

    }

}