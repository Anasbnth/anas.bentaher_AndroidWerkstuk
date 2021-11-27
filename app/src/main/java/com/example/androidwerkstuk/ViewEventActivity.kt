package com.example.androidwerkstuk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.androidwerkstuk.entities.SubscribedUserEventRelation
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.google.firebase.auth.FirebaseAuth

class ViewEventActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var eventViewModel: EventViewModel

    private lateinit var title : TextView
    private lateinit var description : TextView
    private lateinit var creator : TextView
    private lateinit var beginDate : TextView
    private lateinit var endDate : TextView
    private lateinit var subscribeButton : Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewevent)

        auth = FirebaseAuth.getInstance();
        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)


        val intent = intent
        title = findViewById<TextView>(R.id.textfield_title_viewEvent)
        description = findViewById<TextView>(R.id.textfield_description_viewEvent)
        creator = findViewById<TextView>(R.id.textfield_emailCreator_viewEvent)
        beginDate = findViewById<TextView>(R.id.textfield_beginDate_viewEvent)
        endDate = findViewById<TextView>(R.id.textfield_endDate_viewEvent)
        subscribeButton = findViewById<Button>(R.id.button_subscribe_viewEvent)


        title.setText(intent.getStringExtra("title"))
        description.setText(intent.getStringExtra("description"))
        creator.setText(intent.getStringExtra("emailCreator"))
        beginDate.setText(intent.getStringExtra("beginDate"))
        endDate.setText(intent.getStringExtra("endDate"))

        subscribeButton.setOnClickListener {

            val eventwithUsers = SubscribedUserEventRelation(auth.currentUser!!.email!!,intent.getLongExtra("eventID",1))
            eventViewModel.addEventWithUsersSubscribed(eventwithUsers)
            Toast.makeText(this,"Subscribed on this event succesfully", Toast.LENGTH_SHORT).show()

        }
    }


}