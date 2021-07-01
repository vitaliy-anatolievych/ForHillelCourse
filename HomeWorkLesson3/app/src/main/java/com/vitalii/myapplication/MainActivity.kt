package com.vitalii.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendarView = findViewById<View>(R.id.calendar_layout)
        calendarView.setOnClickListener {
            startActivity(Intent(this, CalendarActivity::class.java))
        }
    }
}