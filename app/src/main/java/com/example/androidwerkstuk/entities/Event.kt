package com.example.androidwerkstuk.entities

import android.text.Editable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.androidwerkstuk.converters.LocalDateTimeConverter
import java.time.LocalDateTime
import java.time.OffsetDateTime


@Entity(tableName = "events_table")
data class Event(
    @PrimaryKey(autoGenerate = true) var eventId: Long = 0,
    var title: String,
    var description: String,
    var beginDate: String,
    var endDate: String,
    var emailCreator: String,
    var street: String,
    var huisNr: Int,
    var city: String,
    var zipCode: Int,
                )