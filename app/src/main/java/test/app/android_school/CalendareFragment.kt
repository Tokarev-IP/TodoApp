package test.app.android_school

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.FragmentManager
import com.google.android.material.navigation.NavigationView
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class CalendareFragment : Fragment() {

    private val KEY = "KEY"

    companion object {
        fun newInstance(): CalendareFragment {
            return CalendareFragment()
        }
    }

    lateinit var stringData: String

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_calendare, container, false)

        stringData = SimpleDateFormat("dd-MM-yyyy").format(Date())

        val calendare: CalendarView = mInflater.findViewById(R.id.calendare_view)

        calendare.setOnDateChangeListener { view, year, month, dayOfMonth ->
            stringData = dayOfMonth.toString()+"-" + month.toString()+"-" + year.toString()
        }

        val okButton: Button = mInflater.findViewById(R.id.ok_calendare_button)
        okButton.setOnClickListener {
            val activityTextView = activity?.findViewById<TextView>(R.id.data_done)
            if (activityTextView != null) {
                activityTextView.text = stringData
            }
                activity
                    ?.supportFragmentManager
                    ?.beginTransaction()
                    ?.remove(this)
                    ?.commit()
        }

        val cancelButton: Button = mInflater.findViewById(R.id.cancel_calendare_button)
        cancelButton.setOnClickListener {
            activity
                ?.supportFragmentManager
                ?.beginTransaction()
                ?.remove(this)
                ?.commit()
        }

        return mInflater
    }
}

