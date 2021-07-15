package test.app.android_school.addTaskFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.R
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.recycler.TaskData
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.EntityTaskData
import java.text.SimpleDateFormat
import java.util.*

class AddTaskFragment(private val myViewModel: MyViewModel) : Fragment() {

    private val LONG = "long"
    lateinit var priority: String
    lateinit var id: String

    @SuppressLint("UseSwitchCompatOrMaterialCode", "SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_add_task, container, false)

        val dataText: TextView = mInflater.findViewById(R.id.data_done)
        val okButton: Button = mInflater.findViewById(R.id.ok_button)
        val cancelButton: ImageButton = mInflater.findViewById(R.id.cancel_image_button)
        val mSpinner: Spinner = mInflater.findViewById(R.id.spinner)
        val mEditText: EditText = mInflater.findViewById(R.id.task_edit_text)
        val switch_calendare: Switch = mInflater.findViewById(R.id.switch_view)

        val time = (System.currentTimeMillis()/1000).toInt()

        arguments?.getLong(LONG)?.let {
            dataText.text = SimpleDateFormat("dd-MM-yyyy").format(it)
        }

        switch_calendare.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                (context as AppCompatActivity).supportFragmentManager
                    .beginTransaction()
                    .add(R.id.calendar_frame, CalendareFragment(myViewModel))
                    .commit()
            } else {
                dataText.text = ""
            }
        }

        okButton.setOnClickListener {

            when (mSpinner.selectedItemPosition){
                0 -> priority = "low"
                1 -> priority = "basic"
                2 -> priority = "important"
            }

            if (mEditText.text.toString() == "")
                Toast.makeText(
                        context as AppCompatActivity,
                        "Введите задачу",
                        Toast.LENGTH_LONG)
                        .show()

            else {
                id = UUID.randomUUID().toString()

                myViewModel.updateListOfTasks(
                    EntityTaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                        time,
                        time,
                        time
                    ),
                    TaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                        time,
                        time,
                        time,
                    ),
                    ApiEntityTaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                        time,
                        time,
                        time,
                        "insert"
                    ),
                    context as AppCompatActivity)

                (context as AppCompatActivity).supportFragmentManager
                    .beginTransaction()
                    .remove(this)
                    .commit()
            }
        }

        cancelButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .remove(this)
                .commit()
        }

        return mInflater
    }

}