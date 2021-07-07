package test.app.android_school.recycler

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter

import androidx.recyclerview.widget.RecyclerView
import test.app.android_school.R

class MyRecyclerAdapter: ListAdapter<TaskData, MyViewHolder>(MyDiffUtil()) {

    private var taskList: ArrayList<TaskData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = getItem(position).text
        holder.dataTextView.text = getItem(position).done.toString()
    }

    fun updateAdapter(mTaskList: ArrayList<TaskData>) {
        taskList = mTaskList
    }
}