package test.app.android_school.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ListAdapter
import test.app.android_school.R
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.room.EntityTaskData

class MyRecyclerAdapter(private val appCompatActivity: AppCompatActivity, private val myViewModel: MyViewModel) :
    ListAdapter<EntityTaskData, MyViewHolder>(MyDiffUtil()) {

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
            myViewModel.makeIsDone(
                EntityTaskData(
                    getItem(position).id,
                    getItem(position).text,
                    getItem(position).importance,
                    true,
                    getItem(position).deadline,
                    getItem(position).createdAt,
                    getItem(position).updatedAt,
                ), appCompatActivity
            )

        }

        holder.taskDelete.setOnClickListener {
            myViewModel.deleteTask(
                EntityTaskData(
                    getItem(position).id,
                    getItem(position).text,
                    getItem(position).importance,
                    getItem(position).done,
                    getItem(position).deadline,
                    getItem(position).createdAt,
                    getItem(position).updatedAt,
                ), appCompatActivity
            )
        }
    }
}