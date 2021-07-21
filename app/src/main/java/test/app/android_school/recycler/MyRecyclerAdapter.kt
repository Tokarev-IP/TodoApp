package test.app.android_school.recycler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ListAdapter
import com.google.android.material.timepicker.TimeFormat
import test.app.android_school.R
import test.app.android_school.addTaskFragment.AddTaskFragment
import test.app.android_school.addTaskFragment.ReWriteTaskFragment
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.room.EntityTaskData

class MyRecyclerAdapter(private val appCompatActivity: AppCompatActivity, private val myViewModel: MyViewModel) :
    ListAdapter<EntityTaskData, MyViewHolder>(MyDiffUtil()) {

    private val STRING = "string"
    private val LONG = "long"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.recycler_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = getItem(position).text

        if (getItem(position).done)
            holder.imageCheck.setBackgroundResource(R.drawable.ic_baseline_check_box_25)

        getItem(position).deadline?.let {
            if (it< System.currentTimeMillis() && !getItem(position).done)
                holder.imageCheck.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_25)
            if (it> System.currentTimeMillis() && !getItem(position).done)
                holder.imageCheck.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_25_gray)
        }

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
                )
            )

        }

        holder.taskDelete.setOnClickListener {
            Log.d("ADAPTER_POSITION", position.toString())
            myViewModel.deleteTask(
                EntityTaskData(
                    getItem(position).id,
                    getItem(position).text,
                    getItem(position).importance,
                    getItem(position).done,
                    getItem(position).deadline,
                    getItem(position).createdAt,
                    getItem(position).updatedAt,
                ),
                TaskData(
                    getItem(position).id,
                    getItem(position).text,
                    getItem(position).importance,
                    getItem(position).done,
                    getItem(position).deadline,
                    getItem(position).createdAt,
                    getItem(position).updatedAt,
                )
            )

        }

        holder.infoButton.setOnClickListener {
            val reWriteFragment = ReWriteTaskFragment(myViewModel)
            val bundle = Bundle()
            bundle.putString(STRING, getItem(position).text)

//            getItem(position).deadline.let{ it1 -> bundle.putLong(LONG, it1) }

            reWriteFragment.arguments = bundle

            appCompatActivity.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_frame, reWriteFragment)
                    .commit()
        }
    }
}