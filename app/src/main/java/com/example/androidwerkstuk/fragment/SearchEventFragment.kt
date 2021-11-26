package com.example.androidwerkstuk.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwerkstuk.R
import com.example.androidwerkstuk.adapter.ListAdapter
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.entities.User
import com.example.androidwerkstuk.viewmodel.EventViewModel
import com.example.androidwerkstuk.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseAuth

class SearchEventFragment : Fragment(),ListAdapter.onItemClickListener{

    private lateinit var eventViewModel: EventViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var titleField : EditText
    private lateinit var searchBtn : Button
    val adapter = ListAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = FirebaseAuth.getInstance();





        val view = inflater.inflate(R.layout.fragment_searchforevent,container, false)

        titleField = view.findViewById(R.id.textfield_searchEvent)
        searchBtn = view.findViewById(R.id.button_searchEvent)



        searchBtn.setOnClickListener {


            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewEventsSearch)
            recyclerView.adapter = adapter


            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            eventViewModel = ViewModelProvider(this).get(EventViewModel::class.java)

            eventViewModel.eventsByTitel(titleField.text.toString()).observe(viewLifecycleOwner, Observer {
                    eventList -> adapter.setData(eventList)
            })


        }

        return view
    }

    override fun onItemClick(position: Int) {

        val currentItem : Event = adapter.eventsList[position]
        Toast.makeText(this.context,"Item ${currentItem.title} clicked",Toast.LENGTH_SHORT).show()
    }
}