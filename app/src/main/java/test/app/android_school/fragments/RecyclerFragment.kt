package test.app.android_school.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.R
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.recycler.MyRecyclerAdapter

class RecyclerFragment(private val myViewModel: MyViewModel) : Fragment() {

    private var visible: Boolean = false

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_recycler, container, false)

        val mAdapter = MyRecyclerAdapter(context as AppCompatActivity, myViewModel)
        val mRecyclerView = mInflater.findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        mRecyclerView.adapter = mAdapter

        val floatingButton: FloatingActionButton = mInflater.findViewById(R.id.floating_button)
        val visibleButton: ImageButton = mInflater.findViewById(R.id.complete_tasks_visible)
        val completeTextViw: TextView = mInflater.findViewById(R.id.complete_text_view)
        val topRecycler: TextView = mInflater.findViewById(R.id.my_task_text)

        topRecycler.setOnClickListener {
            mRecyclerView.smoothScrollToPosition(0)
        }

        if (visible) {
            visibleButton.setBackgroundResource(R.drawable.ic_baseline_visibility_off_25)
            myViewModel.getAllTaskData()
        } else {
            visibleButton.setBackgroundResource(R.drawable.ic_baseline_visibility_25)
            myViewModel.getNotDoneTaskData()
        }

        visibleButton.setOnClickListener {
            visible = if (visible) {
                visibleButton.setBackgroundResource(R.drawable.ic_baseline_visibility_25)
                myViewModel.getNotDoneTaskData()
                false
            } else {
                visibleButton.setBackgroundResource(R.drawable.ic_baseline_visibility_off_25)
                myViewModel.getAllTaskData()
                true
            }
        }

        floatingButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_frame, AddTaskFragment(myViewModel))
                .addToBackStack("add_fragment")
                .commit()
        }

        myViewModel.getListOfTasks().observe(viewLifecycleOwner, {
            mAdapter.submitList(it)
            myViewModel.getDoneTaskData()
        })

        myViewModel.getDoneTaskCount().observe(viewLifecycleOwner, {
            completeTextViw.text = "Выполнено - " + it
        })

        return mInflater
    }

}