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
    lateinit var listView : ListView
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: View = inflater.inflate(R.layout.fragment_subscribedevents_list,container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewEventsSubsribed)

        adapter = ListAdapter(this)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)




        return view
    }

    override fun onItemClick(position: Int) {

    }
}