package test.app.android_school.recycler

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.app.android_school.R

class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {

//    val checkBox: CheckBox = v.findViewById(R.id.)
    val textView: TextView = v.findViewById(R.id.text_view)
    val info : ImageButton = v.findViewById(R.id.info_button)
}