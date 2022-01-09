package com.example.androidwerkstuk

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.TextUtils
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.helper.DateTimePicker
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.google.firebase.auth.FirebaseAuth

class CreateNewEventActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var eventViewModel: EventViewModel
    private lateinit var title : EditText
    private lateinit var description : EditText
    private lateinit var beginDate : EditText
    private lateinit var endDate : EditText
    private lateinit var street : EditText
    private lateinit var huisNr : EditText
    private lateinit var city : EditText
    private lateinit var zipcode : EditText
    private lateinit var createEventBtn : Button
    val datetimePicker = DateTimePicker()




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_new_event)

        supportActionBar?.hide()

        auth = FirebaseAuth.getInstance();
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)


        title = findViewById<EditText>(R.id.textfield_title_createEvent)
        description = findViewById<EditText>(R.id.textfield_beschrijving_createEvent)
        beginDate = findViewById<EditText>(R.id.textfield_beginDate_createEvent)
        endDate = findViewById<EditText>(R.id.textfield_endDate_createEvent)
        street = findViewById<EditText>(R.id.textfield_street_createEvent)
        huisNr = findViewById<EditText>(R.id.textfield_huisNr_createEvent)
        city = findViewById<EditText>(R.id.textfield_city_createEvent)
        zipcode = findViewById<EditText>(R.id.textfield_zipcode_createEvent)
        createEventBtn = findViewById<Button>(R.id.button_createNewEvent)


        beginDate.inputType = InputType.TYPE_NULL
        endDate.inputType = InputType.TYPE_NULL




        createEventBtn.setOnClickListener {
            createEvent()

        }

        beginDate.setOnClickListener {

            datetimePicker.showDateTimeDialog(beginDate,this)

        }

        endDate.setOnClickListener {
            datetimePicker.showDateTimeDialog(endDate,this)

        }

    }

    private fun createEvent()
    {
        if(TextUtils.isEmpty(title.text)){
            title.setError(R.string.title.toString() + " " + R.string.notEmpty + ".")
            title.requestFocus()
            return
        }

        else if(TextUtils.isEmpty(description.text)){
            description.setError(R.string.description.toString() + " " + R.string.notEmpty + ".")
            description.requestFocus()
            return

        }


        else if(TextUtils.isEmpty(beginDate.text)){
            beginDate.setError(R.string.beginDate.toString() + " " + R.string.notEmpty + ".")
            beginDate.requestFocus()
            return

        }

        else if(TextUtils.isEmpty(endDate.text)){
            endDate.setError(R.string.endDate.toString() + " " + R.string.notEmpty + ".")
            endDate.requestFocus()
            return

        }

        else if(TextUtils.isEmpty(street.text)){
            street.setError(R.string.street.toString() + " " + R.string.notEmpty + ".")
            street.requestFocus()
            return

        }

        else if(TextUtils.isEmpty(huisNr.text)){
            huisNr.setError(R.string.houseNr.toString() + " " + R.string.notEmpty + ".")
            huisNr.requestFocus()
            return

        }

        else if(TextUtils.isEmpty(city.text)){
            city.setError(R.string.city.toString() + " " + R.string.notEmpty + ".")
            city.requestFocus()
            return

        }

        else if(TextUtils.isEmpty(zipcode.text)){
            zipcode.setError(R.string.zipcode.toString() + " " + R.string.notEmpty + ".")
            zipcode.requestFocus()
            return

        }


        val event = Event(
            0,
            title.text.toString(),
            description.text.toString(),
            beginDate.text.toString(),
            endDate.text.toString(),
            auth.currentUser!!.email!!,
            street.text.toString(),
            huisNr.text.toString().toInt(),
            city.text.toString(),
            zipcode.text.toString().toInt(),
        )

        eventViewModel.addEvent(event)
        Toast.makeText(this, R.string.eventCreated, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))


    }



}