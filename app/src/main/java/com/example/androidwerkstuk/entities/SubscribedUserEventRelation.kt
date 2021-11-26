package com.example.androidwerkstuk.entities

import androidx.room.Entity

@Entity(primaryKeys = ["email","eventId"])
data class SubscribedUserEventRelation(
    var email : String,
    var eventId:Long
)