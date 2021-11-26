package com.example.androidwerkstuk.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(@PrimaryKey var email: String,var username: String,var name: String)
