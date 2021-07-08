package test.app.android_school.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import test.app.android_school.R
import test.app.android_school.room.EntityTaskData

class MyRecyclerAdapter: ListAdapter<EntityTaskData, MyViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = getItem(position).text
        holder.dataTextView.text = getItem(position).done.toString()

        holder.taskComplete.setOnClickListener {

        }

        holder.taskDelete.setOnClickListener {

        }
    }
}