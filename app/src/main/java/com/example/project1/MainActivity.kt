package com.example.project1

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class MainActivity : AppCompatActivity() {
    private var dtetv : TextView? = null
    private var mintv : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePick : Button = findViewById(R.id.btnDatePick)
        dtetv = findViewById(R.id.date)
        mintv = findViewById(R.id.minutes)


        btnDatePick.setOnClickListener {
         //   Toast.makeText(this,"Button Pressed",Toast.LENGTH_LONG).show() (used to check if everything is fine or not)
            clickDatePick()
        }
    }
    
    private fun clickDatePick(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

       val dpd = DatePickerDialog(this,
       DatePickerDialog.OnDateSetListener{
           view, selectedYear, selectedMonth, selectedDay ->
           val selectedDate = "$selectedDay/${selectedMonth+1}/$selectedYear"
           dtetv?.text = selectedDate

           // *** HERE WE CONVERT THE {selectedDate} to real date format so that
           //     we can extract the time from it and it is done using SimpleDateFormat ***


           val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
           val theDate = sdf.parse(selectedDate)
           theDate?.let {
               val selectedDateinMinutes = theDate.time / 60000 // time is millisecond so divide by 60000
               val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
               currentDate?.let {
                   val currentDateInMinutes = currentDate.time/60000
                   val diffInMin = currentDateInMinutes - selectedDateinMinutes
                   mintv?.text = diffInMin.toString()
               }

           }

       },
        year,month,day)

        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}