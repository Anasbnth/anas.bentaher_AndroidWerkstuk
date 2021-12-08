package com.example.androidwerkstuk.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwerkstuk.R
import com.example.androidwerkstuk.ViewEventActivity
import com.example.androidwerkstuk.adapter.ListAdapter
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.example.androidwerkstuk.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class SubscribedEventsFragment : Fragment(), ListAdapter.onItemClickListener {

    private lateinit var userViewModel: UserViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_subscribedevents_list,container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewEventsSubsribed)

        auth = FirebaseAuth.getInstance();
        adapter = ListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        if(auth.currentUser != null)
        {
            userViewModel.userWithSubscribedEvents(auth.currentUser!!.email!!).observe(viewLifecycleOwner, Observer {
                    list -> adapter.setData(list.get(0).events)

            })
        }





        return view
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this.activity,ViewEventActivity::class.java)
        intent.putExtra("eventID",adapter.eventsList[position].eventId)
        intent.putExtra("title",adapter.eventsList[position].title)
        intent.putExtra("description",adapter.eventsList[position].description)
        intent.putExtra("beginDate",adapter.eventsList[position].beginDate)
        intent.putExtra("endDate",adapter.eventsList[position].endDate)
        intent.putExtra("street",adapter.eventsList[position].street)
        intent.putExtra("huisNr",adapter.eventsList[position].huisNr)
        intent.putExtra("city",adapter.eventsList[position].city)
        intent.putExtra("zipcode",adapter.eventsList[position].zipCode)
        intent.putExtra("emailCreator",adapter.eventsList[position].emailCreator)
        this.activity?.startActivity(intent)

    }
}