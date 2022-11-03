package com.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentById(R.id.fragment_container_view_main)
        if (fragment == null) {
            fragment = createFragment()
            fragmentManager.beginTransaction()
                .add(R.id.fragment_container_view_main, fragment)
                .commit()
        }
    }

    private fun createFragment(): Fragment {
        val taskId = intent.getSerializableExtra(KEY_EXTRA_TASK_ID).toString()
        return TaskDetails.newInstance(taskId)
    }
}