package com.example.androidwerkstuk.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidwerkstuk.entities.*

@Dao
interface EventDao {
    @Query("SELECT * FROM events_table ORDER BY title ASC")
    fun getAllEvents(): LiveData<List<Event>>

    @Insert()
    suspend fun insert(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Query("DELETE FROM events_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM events_table WHERE emailCreator = :email")
    fun getEventsFromCreator(email: String): LiveData<List<Event>>

    @Query("SELECT * FROM events_table WHERE title LIKE '%' || :title || '%'")
    fun getEventsByTitel(title: String): LiveData<List<Event>>

    @Transaction
    @Query("SELECT * FROM events_table")
    fun EventwithUsersSubscribed(): List<EventwithUsersSubscribed>






}