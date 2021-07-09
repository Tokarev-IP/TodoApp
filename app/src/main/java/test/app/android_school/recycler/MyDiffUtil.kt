package test.app.android_school.recycler

import androidx.recyclerview.widget.DiffUtil
import test.app.android_school.room.EntityTaskData

class MyDiffUtil: DiffUtil.ItemCallback<EntityTaskData>() {

    override fun areItemsTheSame(oldItem: EntityTaskData, newItem: EntityTaskData): Boolean {
        return oldItem.id == newItem.id &&
                oldItem.done == newItem.done &&
                oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: EntityTaskData, newItem: EntityTaskData): Boolean {
        return oldItem == newItem
    }
}