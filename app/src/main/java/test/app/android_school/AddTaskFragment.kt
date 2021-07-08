package test.app.android_school

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.addTaskActivity.CalendareFragment
import test.app.android_school.addTaskActivity.MyViewModel
import test.app.android_school.room.EntityTaskData
import java.util.*

class AddTaskFragment(private val myViewModel: MyViewModel) : Fragment() {

//    companion object {
//        fun newInstance(myViewModel: MyViewModel): AddTaskFragment {
//            return AddTaskFragment(myViewModel)
//        }
//    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_add_task, container, false)

//        val myViewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val dataText: TextView = mInflater.findViewById(R.id.data_done)
        val okButton: Button = mInflater.findViewById(R.id.ok_button)
        val cancelButton: ImageButton = mInflater.findViewById(R.id.cancel_image_button)
        val mSpinner: Spinner = mInflater.findViewById(R.id.spinner)
        val mEditText: EditText = mInflater.findViewById(R.id.task_edit_text)
        val switch_calendare: Switch = mInflater.findViewById(R.id.switch_view)

        switch_calendare.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                (context as AppCompatActivity).supportFragmentManager
                    .beginTransaction()
                    .add(R.id.calendar_frame, CalendareFragment.newInstance())
                    .commit()
            } else {
                dataText.text = ""
//                taskDate = null
            }
        }

        okButton.setOnClickListener {
            if (mEditText.text.toString() == "")
                Toast.makeText(context as AppCompatActivity, "Введите задачу", Toast.LENGTH_LONG).show()
            else {

                myViewModel.updateListOfTasks(
                    EntityTaskData(
                        UUID.randomUUID().toString(),
                        mEditText.text.toString(),
                        mSpinner.selectedItemPosition.toString(),
                        false,
                        System.currentTimeMillis(),
                        System.currentTimeMillis(),
                        System.currentTimeMillis()
                    ), context as AppCompatActivity)

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