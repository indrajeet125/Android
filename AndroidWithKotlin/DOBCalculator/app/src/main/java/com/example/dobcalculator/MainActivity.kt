package com.example.dobcalculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnDatePicker: Button = findViewById(R.id.btnDatePicker);
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickeDataPicker()
        }
    }

    private fun clickeDataPicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this, { _, SelectedYear, SelectedMonth, SelectedDayOfMonth ->

                val selectedDate = "$SelectedDayOfMonth/${SelectedMonth + 1}/$SelectedYear"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
//                println("${theDate.time} ,${theDate.day},${theDate.month} ,${theDate.year}\t\t")

                theDate?.let {
                    val seletedDateInMinutes = theDate.time / 60000
                    seletedDateInMinutes?.let {
                        val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                        val currentDateInMinutes = currentDate.time / 60000
                        val difference = Math.abs(currentDateInMinutes - seletedDateInMinutes)

                        tvAgeInMinutes?.text = difference.toString()
                    }

                }


            }, year, month, day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}