package test.app.android_school.addTaskActivity

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import test.app.android_school.R
import test.app.android_school.room.EntityTaskData
import java.util.*

class AddTaskActivity(): AppCompatActivity() {

    private var taskDate: Date? = null
    lateinit var taskUpdateDate: Date
    lateinit var taskCreateDate: Date

    @SuppressLint("UseSwitchCompatOrMaterialCode", "ResourceAsColor", "ShowToast")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        val myViewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        val dataText: TextView = findViewById(R.id.data_done)
        val okButton: Button = findViewById(R.id.ok_button)
        val cancelButton: ImageButton = findViewById(R.id.cancel_image_button)
        val mSpinner: Spinner = findViewById(R.id.spinner)
        val mEditText: EditText = findViewById(R.id.task_edit_text)
        val switch_calendare: Switch = findViewById(R.id.switch_view)

        switch_calendare.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.calendar_frame, CalendareFragment.newInstance())
                    .commit()
            } else {
                dataText.text = ""
                taskDate = null
            }
        }

//        okButton.setOnClickListener {
//            if (mEditText.text.toString() == "")
//                Toast.makeText(this, "Введите задачу", Toast.LENGTH_LONG)
//            else {
//                myViewModel.updateListOfTasks(
//                    EntityTaskData(
//                        UUID.randomUUID().toString(),
//                        mEditText.text.toString(),
//                        mSpinner.selectedItemPosition.toString(),
//                        false,
//                        System.currentTimeMillis(),
//                        System.currentTimeMillis(),
//                        System.currentTimeMillis()
//                    )
//                )
//                finish()
//            }
//        }

        cancelButton.setOnClickListener {
            finish()
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun setDate(mTaskDate: Date){
        taskDate = mTaskDate
        taskCreateDate = Date()
        taskUpdateDate = Date()
    }

}