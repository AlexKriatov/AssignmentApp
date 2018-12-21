package com.kriatov.alex.assignmentapp.ui.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.kriatov.alex.assignmentapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (supportFragmentManager.findFragmentById(R.id.root) == null)
            supportFragmentManager.beginTransaction().replace(R.id.root,
                MainFragment()
            ).commit()
    }
}
