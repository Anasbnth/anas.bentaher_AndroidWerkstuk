package com.example.androidwerkstuk.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.androidwerkstuk.db.RoomDB
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.entities.EventwithUsersSubscribed
import com.example.androidwerkstuk.entities.SubscribedUserEventRelation
import com.example.androidwerkstuk.entities.User
import com.example.androidwerkstuk.repositories.EventRepository
import com.example.androidwerkstuk.repositories.UserRepository
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {

    val allEvents: LiveData<List<Event>>
    val repository: EventRepository

    init {
        val eventDao = RoomDB.getDatabase(application).eventDao()
        repository = EventRepository(eventDao)
        allEvents = repository.allEvents
    }

    fun eventsFromCreator(email: String): LiveData<List<Event>> {
        return repository.eventsFromCreator(email)
    }

    fun eventsByTitel(title: String): LiveData<List<Event>> {
        return repository.eventsByTitel(title)
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addEvent(event: Event) = viewModelScope.launch {
        repository.insert(event)
    }

    fun addEventWithUsersSubscribed(ref : SubscribedUserEventRelation) = viewModelScope.launch {
        repository.insertEventWithUsersSubscribed(ref)
    }

    fun updateEvent(event: Event) = viewModelScope.launch {
        repository.updateEvent(event)
    }
}