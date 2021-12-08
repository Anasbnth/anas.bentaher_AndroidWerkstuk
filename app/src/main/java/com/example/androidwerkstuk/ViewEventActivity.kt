package com.example.androidwerkstuk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.entities.SubscribedUserEventRelation
import com.example.androidwerkstuk.entities.User
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.example.androidwerkstuk.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewEventActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var eventViewModel: EventViewModel
    private lateinit var userViewModel: UserViewModel

    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var creator : TextView
    private lateinit var beginDate : TextView
    private lateinit var endDate : TextView
    private lateinit var street : TextView
    private lateinit var huisNr : TextView
    private lateinit var city : TextView
    private lateinit var zipcode : TextView
    private lateinit var numberOfParticipants : TextView
    private lateinit var subscribeOrUnsubscribeBtn : Button
    private lateinit var updateBtn : Button
    private lateinit var deleteBtn : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewevent)

        auth = FirebaseAuth.getInstance();
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)


        val intent = intent
        title = findViewById<TextView>(R.id.textfield_title_viewEvent)
        description = findViewById<TextView>(R.id.textfield_description_viewEvent)
        creator = findViewById<TextView>(R.id.textfield_emailCreator_viewEvent)
        beginDate = findViewById<TextView>(R.id.textfield_beginDate_viewEvent)
        endDate = findViewById<TextView>(R.id.textfield_endDate_viewEvent)
        street = findViewById<TextView>(R.id.textfield_street_viewEvent)
        huisNr = findViewById<TextView>(R.id.textfield_huisNr_viewEvent)
        city = findViewById<TextView>(R.id.textfield_city_viewEvent)
        zipcode = findViewById<TextView>(R.id.textfield_zipcode_viewEvent)
        numberOfParticipants = findViewById<TextView>(R.id.textfield_numberOfParticpants_viewEvent)
        subscribeOrUnsubscribeBtn = findViewById<Button>(R.id.button_subscribeOrUnsubscribe_viewEvent)
        updateBtn = findViewById<Button>(R.id.button_update_viewEvent)
        deleteBtn = findViewById<Button>(R.id.button_delete_viewEvent)




        title.setText(intent.getStringExtra("title"))
        description.setText(intent.getStringExtra("description"))
        creator.setText(intent.getStringExtra("emailCreator"))
        beginDate.setText(intent.getStringExtra("beginDate"))
        endDate.setText(intent.getStringExtra("endDate"))
        street.setText(intent.getStringExtra("street"))
        huisNr.setText((intent.getIntExtra("huisNr",0)).toString())
        city.setText(intent.getStringExtra("city"))
        zipcode.setText((intent.getIntExtra("zipcode",0)).toString())
        val eventId = intent.getLongExtra("eventID",0)





        eventViewModel.EventWithUsersSubscribed(intent.getLongExtra("eventID",0)).observe(this, Observer {
                list ->
            if(list.size != 0) {


                if (list.get(0).users.size != 0) {
                    numberOfParticipants.setText(list.get(0).users.size.toString())
                    userViewModel.getUserByEmail(auth.currentUser!!.email!!)!!
                        .observe(this, Observer { userRoom ->
                            if (list.get(0).users.contains(userRoom)) {
                                subscribeOrUnsubscribeBtn.setText(this.getResources().getString(R.string.unsubscribe))
                            }
                        })
                }
                else {
                    numberOfParticipants.setText("0")
                }
            }
        })

        if(intent.getStringExtra("emailCreator").equals(auth.currentUser!!.email))
        {
            updateBtn.visibility = View.VISIBLE
            deleteBtn.visibility = View.VISIBLE
        }

        updateBtn.setOnClickListener {
            println(eventId)
            val intent = Intent(this, UpdateEventActivity::class.java)
            intent.putExtra("eventID",eventId)
            intent.putExtra("title",title.text)
            intent.putExtra("description",description.text)
            intent.putExtra("beginDate",beginDate.text)
            intent.putExtra("endDate",endDate.text)
            intent.putExtra("street",street.text)
            intent.putExtra("huisNr",huisNr.text.toString().toInt())
            intent.putExtra("city",city.text)
            intent.putExtra("zipcode",zipcode.text.toString().toInt())
            intent.putExtra("emailCreator",creator.text)
            startActivity(intent)
        }

        deleteBtn.setOnClickListener{
            eventViewModel.deleteEvent(intent.getLongExtra("eventID",0))
            this.finish()
            Toast.makeText(this, R.string.eventDeleted, Toast.LENGTH_SHORT)
                .show()

        }


        subscribeOrUnsubscribeBtn.setOnClickListener {


            if(subscribeOrUnsubscribeBtn.text.equals(this.getResources().getString(R.string.subscribe))) {

                val eventwithUsers = SubscribedUserEventRelation(
                    auth.currentUser!!.email!!,
                    intent.getLongExtra("eventID", 1)
                )
                eventViewModel.addEventWithUsersSubscribed(eventwithUsers)
                Toast.makeText(this, R.string.userSubscribed, Toast.LENGTH_SHORT)
                    .show()
                subscribeOrUnsubscribeBtn.setText(this.getResources().getString(R.string.unsubscribe))
            }
            else {


                eventViewModel.unsubscribeUserOnEvent(auth.currentUser!!.email!!,eventId)
                Toast.makeText(this, R.string.userUnsubscribed, Toast.LENGTH_SHORT)
                    .show()
                subscribeOrUnsubscribeBtn.setText(this.getResources().getString(R.string.subscribe))

            }

        }
    }


}