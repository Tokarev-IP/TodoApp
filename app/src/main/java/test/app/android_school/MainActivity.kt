package test.app.android_school

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import test.app.android_school.recycler.MyItemTouchHelper
import test.app.android_school.recycler.MyRecyclerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView = findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = MyRecyclerAdapter()

//        val mItemTouchHelper = MyItemTouchHelper()
//        mItemTouchHelper.attachToRecyclerView(mRecyclerView)
    }

}