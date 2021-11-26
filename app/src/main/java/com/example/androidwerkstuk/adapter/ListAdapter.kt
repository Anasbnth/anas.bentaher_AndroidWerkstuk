package com.example.androidwerkstuk.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidwerkstuk.R
import com.example.androidwerkstuk.entities.Event

class ListAdapter(private val listener : onItemClickListener) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

     var eventsList = emptyList<Event>()




    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view),View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val position : Int = adapterPosition
            if(position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface onItemClickListener {
        fun onItemClick(position : Int)
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.itemView.findViewById<TextView>(R.id.textfield_title_row).text = currentItem.title.toString()
        holder.itemView.findViewById<TextView>(R.id.textfield_description_row).text = currentItem.description.toString()
        holder.itemView.findViewById<TextView>(R.id.textfield_beginDate_row).text = currentItem.beginDate.toString()
        holder.itemView.findViewById<TextView>(R.id.textfield_endDate_row).text = currentItem.endDate.toString()


    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    fun setData(eventList: List<Event>)
    {
        this.eventsList = eventList
        notifyDataSetChanged()
    }


}