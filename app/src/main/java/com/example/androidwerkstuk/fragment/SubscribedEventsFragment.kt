package com.example.androidwerkstuk.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.example.androidwerkstuk.R
import com.example.androidwerkstuk.View_SubscribedEvent_Activity

class SubscribedEventsFragment : Fragment() {

    lateinit var listView : ListView
    lateinit var adapter: ArrayAdapter<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentView: View = inflater.inflate(R.layout.fragment_subscribedevents_list,container, false)
        listView = fragmentView.findViewById<ListView>(R.id.ListView_SubscribedEvent)


        val values : List<String> = listOf("a","da","bh")

        adapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1,android.R.id.text1,values)

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val element = adapter.getItem(position) // The item that was clicked
            val intent = Intent(this.context, View_SubscribedEvent_Activity::class.java)
            startActivity(intent)
        }


        return fragmentView
    }
}