package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val button=findViewById<Button>(R.id.button)
        button.setOnClickListener { view ->
            datePickers(view)
            Log.d("xyz","clicked")
        }

    }
     fun datePickers(view:View){

        val myCalendar= Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)

       val dpd= DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            val chosenYear=year
            val chosenMonth=month
            val chosenDate = "$dayOfMonth/${chosenMonth+1}/$chosenYear"
            selectedDate.text = chosenDate

            val sdf=SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

            val theDate=sdf.parse(chosenDate)
            val chosenDateInMinutes=theDate!!.time/60000

            val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDatesInMinutes=currentDate!!.time/60000

            val differenceInMinutes=currentDatesInMinutes-chosenDateInMinutes

            finalAge.text = differenceInMinutes.toString()


        },year
            ,month
            ,day)
         dpd.datePicker.setMaxDate(Date().time-8640000)
         dpd.show()
    }

}