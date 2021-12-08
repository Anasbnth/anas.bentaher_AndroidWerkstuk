package com.example.androidwerkstuk.helper

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import java.text.SimpleDateFormat
import java.util.*

class DateTimePicker() {

    public fun showDateTimeDialog(date: EditText?,activity: Activity) {
        val c = Calendar.getInstance(Locale.FRANCE)


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(
                view: DatePicker, year: Int, monthOfYear: Int,
                dayOfMonth: Int
            ) {
                c.set(Calendar.YEAR, year)
                c.set(Calendar.MONTH, monthOfYear)
                c.set(Calendar.DAY_OF_MONTH, dayOfMonth)



                val timeSetListener = object : TimePickerDialog.OnTimeSetListener {
                    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                        c.set(Calendar.HOUR_OF_DAY, hourOfDay)
                        c.set(Calendar.MINUTE, minute)

                        val myFormat = "dd-MM-yy HH:mm" // mention the format you need
                        val sdf = SimpleDateFormat(myFormat)
                        date!!.setText(sdf.format(c.time))


                    }

                }
                TimePickerDialog(
                    activity,
                    timeSetListener,
                    c.get(Calendar.HOUR_OF_DAY),
                    c.get(Calendar.MINUTE),
                    false
                ).show()
            }




        }

        DatePickerDialog(activity,dateSetListener,c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(
            Calendar.YEAR)).show()


    }
}