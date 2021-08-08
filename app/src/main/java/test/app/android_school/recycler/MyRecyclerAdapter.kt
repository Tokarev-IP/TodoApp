package test.app.android_school.recycler

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ListAdapter
import test.app.android_school.R
import test.app.android_school.fragments.ReWriteTaskFragment
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.room.ApiEntityTaskData
import test.app.android_school.room.EntityTaskData

class MyRecyclerAdapter(private val appCompatActivity: AppCompatActivity, private val myViewModel: MyViewModel) :
    ListAdapter<EntityTaskData, MyViewHolder>(MyDiffUtil()) {

    private val TEXT = "text"
    private val ID = "id"
    private val CREATEDAT = "created_at"
    private val DEADLINE = "deadLine"

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
        else {
            getItem(position).deadline.apply {
                if (this < (System.currentTimeMillis()/1000L).toInt())
                    holder.imageCheck.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_25)
                if (this > (System.currentTimeMillis()/1000L).toInt())
                    holder.imageCheck.setBackgroundResource(R.drawable.ic_baseline_check_box_outline_blank_25_gray)
            }
        }

        holder.taskComplete.setOnClickListener {
            myViewModel.updateTasks(
                    EntityTaskData(
                            getItem(position).id,
                            getItem(position).text,
                            getItem(position).importance,
                            true,
                            getItem(position).deadline,
                            getItem(position).createdAt,
                            getItem(position).updatedAt,
                    ),
                    ApiEntityTaskData(
                            getItem(position).id,
                            getItem(position).text,
                            getItem(position).importance,
                            true,
                            getItem(position).deadline,
                            getItem(position).createdAt,
                            getItem(position).updatedAt,
                            "update"
                    )
            )
        }

        holder.taskDelete.setOnClickListener {
            Log.d("ADAPTER_POSITION", position.toString())
            myViewModel.updateTasks(
                EntityTaskData(
                    getItem(position).id,
                    getItem(position).text,
                    getItem(position).importance,
                    true,
                    getItem(position).deadline,
                    getItem(position).createdAt,
                    getItem(position).updatedAt,
                ),
                ApiEntityTaskData(
                    getItem(position).id,
                    getItem(position).text,
                    getItem(position).importance,
                    true,
                    getItem(position).deadline,
                    getItem(position).createdAt,
                    getItem(position).updatedAt,
                    "update"
                )
            )

        }

        holder.infoButton.setOnClickListener {
            val reWriteFragment = ReWriteTaskFragment(myViewModel)
            val bundle = Bundle()
            bundle.putString(TEXT, getItem(position).text)
            bundle.putString(ID, getItem(position).id)
            bundle.putInt(CREATEDAT, getItem(position).createdAt)
            bundle.putInt(DEADLINE, getItem(position).deadline)

            reWriteFragment.arguments = bundle

            appCompatActivity.supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_frame, reWriteFragment)
                    .commit()
        }
    }
}