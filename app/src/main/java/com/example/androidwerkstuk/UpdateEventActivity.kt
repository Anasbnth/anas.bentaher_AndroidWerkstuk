package com.example.androidwerkstuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.helper.DateTimePicker
import com.example.androidwerkstuk.viewmodel.EventViewModel

class UpdateEventActivity : AppCompatActivity() {

    private lateinit var title : EditText
    private lateinit var description : EditText
    private lateinit var beginDate : EditText
    private lateinit var endDate : EditText
    private lateinit var street : EditText
    private lateinit var huisNr : EditText
    private lateinit var city : EditText
    private lateinit var zipcode : EditText
    private lateinit var updateBtn : Button
    private lateinit var eventViewModel: EventViewModel
    val datetimePicker = DateTimePicker()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_event)

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

        title = findViewById<EditText>(R.id.textfield_title_updateEvent)
        description = findViewById<EditText>(R.id.textfield_description_updateEvent)
        beginDate = findViewById<EditText>(R.id.textfield_beginDate_updateEvent)
        endDate = findViewById<EditText>(R.id.textfield_endDate_updateEvent)
        street = findViewById<EditText>(R.id.textfield_street_updateEvent)
        huisNr = findViewById<EditText>(R.id.textfield_huisNr_updateEvent)
        city = findViewById<EditText>(R.id.textfield_city_updateEvent)
        zipcode = findViewById<EditText>(R.id.textfield_zipcode_updateEvent)
        updateBtn = findViewById<Button>(R.id.button_updateBtn_updateEvent)

        title.setText(intent.getStringExtra("title"))
        description.setText(intent.getStringExtra("description"))
        beginDate.setText(intent.getStringExtra("beginDate"))
        endDate.setText(intent.getStringExtra("endDate"))
        street.setText(intent.getStringExtra("street"))
        huisNr.setText((intent.getIntExtra("huisNr",0)).toString())
        city.setText(intent.getStringExtra("city"))
        zipcode.setText((intent.getIntExtra("zipcode",0)).toString())

        beginDate.setOnClickListener {
            datetimePicker.showDateTimeDialog(beginDate,this)
        }

        endDate.setOnClickListener {
            datetimePicker.showDateTimeDialog(endDate,this)
        }

        updateBtn.setOnClickListener {
            updateEvent()
        }

    }

    private fun updateEvent()
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
            intent.getLongExtra("eventID",0),
            title.text.toString(),
            description.text.toString(),
            beginDate.text.toString(),
            endDate.text.toString(),
            intent.getStringExtra("emailCreator")!!,
            street.text.toString(),
            huisNr.text.toString().toInt(),
            city.text.toString(),
            zipcode.text.toString().toInt(),
        )

        eventViewModel.updateEvent(event)
        Toast.makeText(this, R.string.eventUpdated, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))


    }
}