package com.example.androidwerkstuk

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.converters.LocalDateTimeConverter
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

class CreateNewEventActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var eventViewModel: EventViewModel
    private lateinit var title : EditText
    private lateinit var beschrijving : EditText
    private lateinit var beginDate : EditText
    private lateinit var endDate : EditText
    private lateinit var createEventBtn : Button




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_event)

        auth = FirebaseAuth.getInstance();
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)


        title = findViewById<EditText>(R.id.textfield_title_createEvent)
        beschrijving = findViewById<EditText>(R.id.textfield_beschrijving_createEvent)
        beginDate = findViewById<EditText>(R.id.textfield_beginDate_createEvent)
        endDate = findViewById<EditText>(R.id.textfield_endDate_createEvent)
        createEventBtn = findViewById<Button>(R.id.button_createNewEvent)


        beginDate.inputType = InputType.TYPE_NULL
        endDate.inputType = InputType.TYPE_NULL




        createEventBtn.setOnClickListener {
            createEvent()

        }

        beginDate.setOnClickListener {
            showDateTimeDialog(beginDate)
        }

        endDate.setOnClickListener {
            showDateTimeDialog(endDate)
        }

    }

    private fun createEvent()
    {
        if(TextUtils.isEmpty(title.text)){
            title.setError("Title cannot be empty.")
            title.requestFocus()
            return
        }

        else if(TextUtils.isEmpty(beschrijving.text)){
            beschrijving.setError("Description cannot be empty.")
            beschrijving.requestFocus()
            return

        }


        else if(TextUtils.isEmpty(beginDate.text)){
            beginDate.setError("Begin date cannot be empty")
            beginDate.requestFocus()
            return

        }

        else if(TextUtils.isEmpty(endDate.text)){
            endDate.setError("End date cannot be empty")
            endDate.requestFocus()
            return

        }

        val converter = LocalDateTimeConverter()
        val event = Event(
            0,
            title.text.toString(),
            beschrijving.text.toString(),
            beginDate.text.toString(),
            endDate.text.toString(),
            auth.currentUser!!.email!!
        )

        eventViewModel.addEvent(event)
        Toast.makeText(this, "Event is Created succesfully", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))


    }


    private fun showDateTimeDialog(date: EditText?) {
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
                    this@CreateNewEventActivity,
                    timeSetListener,
                    c.get(Calendar.HOUR_OF_DAY),
                    c.get(Calendar.MINUTE),
                    false
                ).show()
            }




        }

        DatePickerDialog(this,dateSetListener,c.get(Calendar.DAY_OF_MONTH),c.get(Calendar.MONTH),c.get(Calendar.YEAR)).show()


    }
}