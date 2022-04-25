package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var tvSelectedDate: TextView? = null
    var tvMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvMinutes = findViewById(R.id.tvMinutes)
        btnDatePicker.setOnClickListener() {
            clickDatePicker()
        }

    }

    fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this,
            { _, selectYear, selectMonth, selectDay ->
                Toast.makeText(this, "Year ${selectYear}, Month ${selectMonth+1}, Day ${selectDay}",
                    Toast.LENGTH_LONG).show()

                val selectedDate = "${selectDay}.${selectMonth+1}.${selectYear}"
                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.JAPANESE)
                val theDate = sdf.parse(selectedDate)
                theDate?.let{
                    val theMinutes = theDate.time / (60 * 1000)
                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let{
                        val currentMinutes = currentDate.time / (60 * 1000)
                        val diffInMinites = currentMinutes - theMinutes
                        tvMinutes?.text = diffInMinites.toString()
                    }
                }

            }, year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400 * 1000
        dpd.show()

    }
}