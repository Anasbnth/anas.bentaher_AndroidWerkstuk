package com.example.androidwerkstuk.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwerkstuk.LoginActivity
import com.example.androidwerkstuk.R
import com.example.androidwerkstuk.ViewEventActivity
import com.example.androidwerkstuk.adapter.ListAdapter
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.google.firebase.auth.FirebaseAuth

class CreatedEventsFragment : Fragment(),ListAdapter.onItemClickListener {

    private lateinit var eventViewModel: EventViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var adapter: ListAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance();

        adapter = ListAdapter(this)

        val view = inflater.inflate(R.layout.fragment_createdevents_list,container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)
        eventViewModel.eventsFromCreator(auth.currentUser!!.email!!).observe(viewLifecycleOwner, Observer {
            event -> adapter.setData(event)
        })

        return view
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this.activity,ViewEventActivity::class.java)
        intent.putExtra("eventID",adapter.eventsList[position].eventId)
        intent.putExtra("title",adapter.eventsList[position].title)
        intent.putExtra("description",adapter.eventsList[position].description)
        intent.putExtra("beginDate",adapter.eventsList[position].beginDate)
        intent.putExtra("endDate",adapter.eventsList[position].endDate)
        intent.putExtra("emailCreator",adapter.eventsList[position].emailCreator)
        this.activity?.startActivity(intent)
    }
}
