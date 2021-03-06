package test.app.android_school.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import test.app.android_school.R
import test.app.android_school.mvvm.MyViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.properties.Delegates


class CalendareFragment(private val myViewModel: MyViewModel) : Fragment() {

    var time by Delegates.notNull<Int>()

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_calendare, container, false)

        val calendare: CalendarView = mInflater.findViewById(R.id.calendare_view)

        val okButton: Button = mInflater.findViewById(R.id.ok_calendare_button)

        val mAddTaskFragment = AddTaskFragment(myViewModel)

        time = (calendare.date/1000).toInt()

        calendare.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val stringDate: String = dayOfMonth.toString()+" "+(month+1).toString()+" "+year.toString()
            Log.d("TAG", stringDate )
            val date: Date = SimpleDateFormat("dd MM yyyy").parse(stringDate)
            time = ((date.time)/1000).toInt()
            Log.d("TAG", time.toString() )
        }

        okButton.setOnClickListener {
            myViewModel.setTime(time)

            (context as AppCompatActivity)
                    .supportFragmentManager
                    .beginTransaction()
                    .remove(this)
                    .commit()
        }

        val cancelButton: Button = mInflater.findViewById(R.id.cancel_calendare_button)
        cancelButton.setOnClickListener {
            (context as AppCompatActivity)
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_frame ,mAddTaskFragment)
                    .commit()
        }
        return mInflater
    }
}

