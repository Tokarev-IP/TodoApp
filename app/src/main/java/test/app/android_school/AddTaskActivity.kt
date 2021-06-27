package test.app.android_school

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import test.app.android_school.recycler.DtClass

class AddTaskActivity: AppCompatActivity() {

    private val TAG = "TAG"
    private val TAAG = "TAAG"
    private val TAAAG = "TAAAG"
    private val TAAAAG = "TAAAAG"

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;

        val dataText: TextView = findViewById(R.id.data_done)
        val mIntent = Intent()

        val cancelButton: ImageButton = findViewById(R.id.cancel_image_button)
        val okButton: Button = findViewById(R.id.ok_button)
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
            }
        }

        okButton.setOnClickListener {
            mIntent.putExtra(TAG, dataText.toString())
            mIntent.putExtra(TAAG, mEditText.text.toString())
            mIntent.putExtra(TAAAG, false)
            mIntent.putExtra(TAAAAG, mSpinner.selectedItem.toString())
            setResult(Activity.RESULT_OK, mIntent)
            finish()
        }

        cancelButton.setOnClickListener {
            setResult(Activity.RESULT_CANCELED, mIntent)
            finish()
        }
    }

    fun sendData(data: DtClass){
        val dataIntent = Intent()
    }
}