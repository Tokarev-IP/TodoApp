package test.app.android_school.addTaskActivity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.MyViewModel
import test.app.android_school.R
import test.app.android_school.recycler.TaskData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AddTaskActivity: AppCompatActivity() {

    private var taskDate: Date? = null
    private val myViewModel = MyViewModel()

    @SuppressLint("UseSwitchCompatOrMaterialCode", "ResourceAsColor")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        val mIntent = Intent()

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

        okButton.setOnClickListener {
            myViewModel.updateListOfTasks(TaskData(
                    taskDate,
                    mEditText.text.toString(),
                    false,
                    mSpinner.selectedItemPosition,
            ), arrayListOf(TaskData(
                    taskDate,
                    mEditText.text.toString(),
                    false,
                    mSpinner.selectedItemPosition,
            ))
            )
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, mIntent)
            finish()
        }

    }

    @SuppressLint("SimpleDateFormat")
    fun setDate(mTaskDate: Date){
        taskDate = mTaskDate
    }

}