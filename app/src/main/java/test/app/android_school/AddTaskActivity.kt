package test.app.android_school

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.recycler.DtClass

class AddTaskActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)


    }

    fun sendData(data: DtClass){
        val dataIntent = Intent()
    }
}