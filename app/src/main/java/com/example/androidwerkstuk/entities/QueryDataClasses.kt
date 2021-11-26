package com.example.androidwerkstuk.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

    data class UserWithEventsOnSubscribed(
        @Embedded var user: User,
        @Relation(
            parentColumn = "email",
            entityColumn = "eventId",
            entity = Event::class,
            associateBy = Junction(SubscribedUserEventRelation::class)
        )
        var events: List<Event>
    )

    data class EventwithUsersSubscribed(
        @Embedded var event: Event,
        @Relation(
            parentColumn = "eventId",
            entity = User::class,
            entityColumn = "email",
            associateBy = Junction(SubscribedUserEventRelation::class)
        )
        var users: List<User>
    )
