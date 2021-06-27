package test.app.android_school

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.recycler.DtClass
import test.app.android_school.recycler.MyRecyclerAdapter

class MainActivity : AppCompatActivity() {

    private val CODE = 1
    private val taskList = mutableListOf<DtClass>()
    private val TAG = "TAG"
    private val TAAG = "TAAG"
    private val TAAAG = "TAAAG"
    private val TAAAAG = "TAAAAG"

    val myAdapter = MyRecyclerAdapter()

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = MyRecyclerAdapter()

        val floatingButton: FloatingActionButton = findViewById(R.id.floating_button)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        floatingButton.setOnClickListener {
            val intentActivityResult = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intentActivityResult, CODE)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE) {
            if(resultCode == Activity.RESULT_OK){
                if (data != null) {
                    taskList.add(DtClass(data.getStringExtra(TAG),
                        data.getStringExtra(TAAG),
                    false,
                    data.getStringExtra(TAAAAG)))
                }
            }
            myAdapter.updateAdapter(taskList)
        }
    }

}