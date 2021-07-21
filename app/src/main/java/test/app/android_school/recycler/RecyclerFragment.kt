package test.app.android_school.recycler

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.R
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.addTaskFragment.AddTaskFragment
import javax.inject.Inject

class RecyclerFragment : Fragment() {

    companion object {
        fun newInstance(): RecyclerFragment {
            return RecyclerFragment()
        }
    }

    @Inject
    lateinit var myViewModel: MyViewModel

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
        val visible_button: ImageButton = mInflater.findViewById(R.id.complete_tasks_visible)
        val completeTextViw: TextView = mInflater.findViewById(R.id.complete_text_view)
        val topRecycler: TextView = mInflater.findViewById(R.id.my_task_text)

        topRecycler.setOnClickListener {
            mRecyclerView.smoothScrollToPosition(0)
        }

        if (visible) {
            visible_button.setBackgroundResource(R.drawable.ic_baseline_visibility_off_25)
            myViewModel.getAllTaskData()
        } else {
            visible_button.setBackgroundResource(R.drawable.ic_baseline_visibility_25)
            myViewModel.getNotDoneTaskData()
        }

        visible_button.setOnClickListener {
            if (visible) {
                visible_button.setBackgroundResource(R.drawable.ic_baseline_visibility_25)
                myViewModel.getNotDoneTaskData()
                visible = false
            } else {
                visible_button.setBackgroundResource(R.drawable.ic_baseline_visibility_off_25)
                myViewModel.getAllTaskData()
                visible = true
            }
        }

        floatingButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_frame, AddTaskFragment())
                .addToBackStack("add_fragment")
                .commit()
        }

        myViewModel.getListOfTasks().observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
            myViewModel.getDoneTaskData()
        })

        myViewModel.getDoneTaskCount().observe(viewLifecycleOwner, Observer {
            completeTextViw.text = "Выполнено - " + it
        })

        return mInflater
    }

}