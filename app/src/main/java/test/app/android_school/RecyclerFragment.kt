package test.app.android_school

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import test.app.android_school.addTaskActivity.MyViewModel
import test.app.android_school.recycler.MyRecyclerAdapter
import test.app.android_school.room.DataBaseTask

class RecyclerFragment : Fragment() {

    companion object {
        fun newInstance(): RecyclerFragment {
            return RecyclerFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val mInflater = inflater.inflate(R.layout.fragment_recycler, container, false)

        val mAdapter = MyRecyclerAdapter()
        val mRecyclerView = mInflater.findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        mRecyclerView.adapter = mAdapter

        val floatingButton: FloatingActionButton = mInflater.findViewById(R.id.floating_button)

        val myViewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val dbDao = DataBaseTask.getDatabase(context as AppCompatActivity).taskDao()

        myViewModel.getAllTaskData(context as AppCompatActivity)

        floatingButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_frame, AddTaskFragment(myViewModel))
                .commit()
        }

        myViewModel.getListOfTasks().observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
            Log.d("TAG", "List submitted")
        })

        return mInflater
    }

}