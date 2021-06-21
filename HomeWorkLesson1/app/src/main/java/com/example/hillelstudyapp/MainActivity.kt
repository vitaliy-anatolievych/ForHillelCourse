package com.example.hillelstudyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.MyButton);
        val text = findViewById<TextView>(R.id.MyText);

        button.setOnClickListener {
            text.text = "Hello, my name is Vitalii Cherneta"
        }
    }
}