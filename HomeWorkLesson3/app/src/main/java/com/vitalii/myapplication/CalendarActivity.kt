package com.vitalii.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val dateEditText = findViewById<EditText>(R.id.editTextTextMultiLine)
        val arrayDate: ArrayList<String> = ArrayList()

        val validateBtn = findViewById<Button>(R.id.validate_btn)
        val resultTxt = findViewById<TextView>(R.id.result)
        validateBtn.setOnClickListener {
            Log.i("Input", "${dateEditText.text}")
            arrayDate.addAll(dateEditText.text.split("\n"))
            Log.i("Array", Arrays.toString(arrayDate.toArray()))

            if (dateEditText.text.isEmpty()) {
                resultTxt.text = "Поле пустое"
                resultTxt.setTextColor(Color.RED)
            } else {
                if (calculateResult(arrayDate)) {
                    resultTxt.text = "Прибывание разрешено"
                    resultTxt.setTextColor(Color.GREEN)
                } else {
                    resultTxt.text = "Прибывание запрещенно"
                    resultTxt.setTextColor(Color.RED)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateResult(arrayDate: ArrayList<String>): Boolean {
        var summaryDay: Long = 0


        for (date in arrayDate.indices) {
            val dateStart: String = arrayDate[date].substringAfter("-").trim()
            val dateEnd: String = arrayDate[date].substringBefore("-").trim()

            val formatDateStart: Date? = SimpleDateFormat("dd.MM.yyyy").parse(dateStart)
            val formatDateEnd: Date? = SimpleDateFormat("dd.MM.yyyy").parse(dateEnd)
            Log.i("Date", "$formatDateStart | $formatDateEnd")

            val differenceDate: Long = (formatDateEnd?.time ?: 0) - (formatDateStart?.time ?: 0)
            summaryDay += differenceDate / (1000 * 60 * 60 * 24)
        }


        Log.i("Summary", "$summaryDay")
        return false
    }
}