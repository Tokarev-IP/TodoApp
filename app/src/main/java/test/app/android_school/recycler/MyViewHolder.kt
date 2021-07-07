package test.app.android_school.recycler

import android.view.View
import android.widget.CheckBox
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import test.app.android_school.R

class MyViewHolder(v: View): RecyclerView.ViewHolder(v) {

    val textView: TextView = v.findViewById(R.id.text_view)
    val info : ImageButton = v.findViewById(R.id.info_button)
    val dataTextView: TextView = v.findViewById(R.id.data_text_view)
    val taskComplete: ImageButton = v.findViewById(R.id.image_view_left)
    val taskDelete: ImageButton = v.findViewById(R.id.image_view_right)
}