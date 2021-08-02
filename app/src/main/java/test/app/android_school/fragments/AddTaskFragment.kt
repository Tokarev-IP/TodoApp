package test.app.android_school.fragments

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
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.EntityTaskData
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates

class AddTaskFragment(private val myViewModel: MyViewModel) : Fragment() {

    lateinit var priority: String
    lateinit var id: String
    private var deadLine : Int = (System.currentTimeMillis()/1000L).toInt()
    private val time: Int = (System.currentTimeMillis()/1000L).toInt()

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

        dataText.text = SimpleDateFormat("dd-MM-yyyy").format(deadLine*1000L)

        myViewModel.getTime().observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            dataText.text = SimpleDateFormat("dd-MM-yyyy").format(it*1000L)
            deadLine  = it
        })

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

                myViewModel.insertTaskToRoom(
                    EntityTaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                            deadLine ,
                            time,
                            time
                    ),
                    ApiEntityTaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                            deadLine ,
                            time,
                            time,
                        "insert"
                    ))

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