package com.example.androidwerkstuk.repositories

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.androidwerkstuk.dao.EventDao
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.entities.EventwithUsersSubscribed
import com.example.androidwerkstuk.entities.SubscribedUserEventRelation
import com.example.androidwerkstuk.entities.UserWithEventsOnSubscribed

class EventRepository(private val eventDao: EventDao) {
    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allEvents: LiveData<List<Event>> = eventDao.getAllEvents()



    fun eventsFromCreator(email: String): LiveData<List<Event>> {
        return eventDao.getEventsFromCreator(email)
    }

    fun eventsByTitel(title: String): LiveData<List<Event>> {
        return eventDao.getEventsByTitel(title)
    }

    fun EventWithUsersSubscribed(eventID : Long): LiveData<List<EventwithUsersSubscribed>> {
        return eventDao.EventwithUsersSubscribed(eventID)
    }





    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(event: Event) {
        eventDao.insertEvent(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertEventWithUsersSubscribed(ref : SubscribedUserEventRelation) {
        eventDao.insertEventwithUsersSubscribed(ref)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateEvent(event: Event){
        eventDao.updateEvent(event)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(eventId: Long) {
        eventDao.deleteEventById(eventId)
    }


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun unsubscribeUserOnEvent(email: String,eventId: Long) {
        eventDao.unsubscribeUserOnEvent(email,eventId)
    }


}