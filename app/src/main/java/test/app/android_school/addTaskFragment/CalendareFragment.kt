package test.app.android_school.addTaskFragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import test.app.android_school.R
import java.text.SimpleDateFormat

class CalendareFragment : Fragment() {

    companion object {
        fun newInstance(): CalendareFragment {
            return CalendareFragment()
        }
    }

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_calendare, container, false)

        val calendare: CalendarView = mInflater.findViewById(R.id.calendare_view)

        val okButton: Button = mInflater.findViewById(R.id.ok_calendare_button)
        okButton.setOnClickListener {

            val activityTextView = activity?.findViewById<TextView>(R.id.data_done)
            if (activityTextView != null) {
                activityTextView.text = SimpleDateFormat("dd-MM-yyyy").format(calendare.date)
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

