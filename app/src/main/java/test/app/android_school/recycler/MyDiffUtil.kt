package test.app.android_school.recycler

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil: DiffUtil.Callback() {

    private var oldList: List<TaskData> = listOf()
    private var newList: List<TaskData> = listOf()

    override fun getOldListSize(): Int {
        TODO("Not yet implemented")
    }

    override fun getNewListSize(): Int {
        TODO("Not yet implemented")
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        TODO("Not yet implemented")
    }
}