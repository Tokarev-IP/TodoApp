package test.app.android_school

import android.app.StatusBarManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.recycler.DtClass
import test.app.android_school.recycler.MyRecyclerAdapter

class MainActivity : AppCompatActivity() {

    val taskList: MutableList<DtClass> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = MyRecyclerAdapter()

        val floatingButton: FloatingActionButton = findViewById(R.id.floating_button)

//        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
//        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
//            result ->
//            if (result.resultCode == Activity.RESULT_OK){
//                val dataResult: Intent? = result.data
//            }
//
//        }

        floatingButton.setOnClickListener {
            val intentActivityResult = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intentActivityResult, 1)
        }


    }

}