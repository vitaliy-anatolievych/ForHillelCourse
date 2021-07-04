package com.vitalii.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.SimpleDateFormat
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
            arrayDate.addAll(dateEditText.text.split("\n"))

            if (dateEditText.text.isEmpty()) {
                resultTxt.text = "Поле пустое"
                resultTxt.setTextColor(Color.RED)
            } else {
                val daysInEU = calculateResult(arrayDate)
                if (daysInEU < 90) {
                    resultTxt.text = getString(R.string.days_left, 90 - daysInEU)
                    resultTxt.setTextColor(Color.GREEN)
                } else {
                    resultTxt.text = getString(R.string.alert_days, 90 - daysInEU)
                    resultTxt.setTextColor(Color.RED)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun calculateResult(arrayDate: ArrayList<String>): Long {
        var timeSpentInTheEU: Long = 0

        for (date in arrayDate.indices) {
            val dateOfEntry = arrayDate.get(date).substringBefore("-").trim()
            val dateOfLeave = arrayDate.get(date).substringAfter("-").trim()
            val dateSixMonthAgo = findTheDateSixMonthsAgo(dateOfLeave)


            timeSpentInTheEU = calculateDays(dateSixMonthAgo, dateOfLeave, arrayDate)

        }

        return timeSpentInTheEU
    }


    /**
     * Метод который берёт отрезок начальная точка: @dateSixMonthAgo конечная точка @dateOfLeave
     * Цель: найти даты въезда-выезда в ЕС в этом интервале и посчитать количество проведённых дней
     */
    private fun calculateDays(dateSixMonthAgo: String, dateOfLeave: String,  arrayDate: List<String>): Long {
        val formatDaySixMonthAgo = SimpleDateFormat("dd.MM.yyyy").parse(dateSixMonthAgo)
        val formatDayLeave = SimpleDateFormat("dd.MM.yyyy").parse(dateOfLeave)

        var summaryDays: Long = 0

        for (date in arrayDate.indices) {
            val currentDateOfEntry = SimpleDateFormat("dd.MM.yyyy").parse(arrayDate.get(date).substringBefore("-").trim())
            val currentDateOfLeave = SimpleDateFormat("dd.MM.yyyy").parse(arrayDate.get(date).substringAfter("-").trim())

            if (currentDateOfEntry.time > formatDaySixMonthAgo.time && currentDateOfEntry.time < formatDayLeave.time) {
                summaryDays += (currentDateOfLeave.time - currentDateOfEntry.time) / (1000 * 60 * 60 * 24)
            }
        }

        return summaryDays
    }

    /**
     * Метод поиска даты которая была пол года назад
     * @endOfStayInEU - Дата -180 дней от дня въезда в миллисекундах
     * @calendar - форматирование в привычную дату
     * @return возвращаем дату в виде текста
     */
    private fun findTheDateSixMonthsAgo(dateOfLeave: String): String {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val endOfStayInEU = dateFormat.parse(dateOfLeave).time - 1.555e+10

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = endOfStayInEU.toLong()

        return dateFormat.format(calendar.timeInMillis)
    }
}