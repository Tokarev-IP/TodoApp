package test.app.android_school.recycler

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
import test.app.android_school.R
import test.app.android_school.mvvm.MyViewModel
import test.app.android_school.addTaskFragment.AddTaskFragment

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

        val myViewModel: MyViewModel = ViewModelProvider(this).get(MyViewModel::class.java)

        val mAdapter = MyRecyclerAdapter(context as AppCompatActivity, myViewModel)
        val mRecyclerView = mInflater.findViewById<RecyclerView>(R.id.recycler)
        mRecyclerView.layoutManager = LinearLayoutManager(context as AppCompatActivity)
        mRecyclerView.adapter = mAdapter

        val floatingButton: FloatingActionButton = mInflater.findViewById(R.id.floating_button)

        myViewModel.getAllTaskData(context as AppCompatActivity)

        floatingButton.setOnClickListener {
            (context as AppCompatActivity).supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_frame, AddTaskFragment(myViewModel))
                .addToBackStack("add_fragment")
                .commit()
        }

        myViewModel.getListOfTasks().observe(viewLifecycleOwner, Observer {
            mAdapter.submitList(it)
            Log.d("TAG", "List submitted")
        })

        return mInflater
    }

}