package test.app.android_school.recycler

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil: DiffUtil.ItemCallback<TaskData>() {

    override fun areItemsTheSame(oldItem: TaskData, newItem: TaskData): Boolean {
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: TaskData, newItem: TaskData): Boolean {
        return oldItem == newItem
    }
}