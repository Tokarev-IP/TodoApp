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
import javax.inject.Inject
import kotlin.properties.Delegates

class ReWriteTaskFragment @Inject constructor(private val myViewModel: MyViewModel) : Fragment() {

    private val TEXT = "text"
    private val ID = "id"
    private val CREATEDAT = "created_at"
    private val DEADLINE = "deadLine"

    private var deadLine by Delegates.notNull<Int>()
    private var createdAt by Delegates.notNull<Int>()

    lateinit var id: String
    lateinit var priority: String

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

        when (mSpinner.selectedItemPosition){
            0 -> priority = "low"
            1 -> priority = "basic"
            2 -> priority = "important"
        }

        arguments?.getString(TEXT)?.let {
            mEditText.setText(it)
        }

        arguments?.getString(ID)?.let {
            id = it
        }

        arguments?.getInt(DEADLINE)?.let {
            dataText.text = SimpleDateFormat("dd-MM-yyyy").format(it)
            deadLine = it
        }

        arguments?.getInt(CREATEDAT)?.let {
            createdAt = it
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

        myViewModel.getTime().observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            dataText.text = SimpleDateFormat("dd-MM-yyyy").format(it*1000L)
            deadLine  = it
        })

        okButton.setOnClickListener {
            if (mEditText.text.toString() == "")
                Toast.makeText(
                        context as AppCompatActivity,
                        "Введите задачу",
                        Toast.LENGTH_LONG)
                        .show()
            else {
                myViewModel.updateTasks(
                    EntityTaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                        deadLine,
                        createdAt,
                        (System.currentTimeMillis()/1000L).toInt()
                        ),
                    ApiEntityTaskData(
                        id,
                        mEditText.text.toString(),
                        priority,
                        false,
                        deadLine,
                        createdAt,
                        (System.currentTimeMillis()/1000L).toInt(),
                        "update"
                    ),
                )

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