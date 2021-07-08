package test.app.android_school.mvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import test.app.android_school.R
import test.app.android_school.recycler.RecyclerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_frame, RecyclerFragment.newInstance())
            .commit()

    }

}