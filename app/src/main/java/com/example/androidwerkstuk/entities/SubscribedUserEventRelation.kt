package com.example.androidwerkstuk.entities

import androidx.room.Entity


@Entity(primaryKeys = ["email","eventId"],tableName = "user_event_table")
data class SubscribedUserEventRelation(
    var email : String,
    var eventId:Long
)