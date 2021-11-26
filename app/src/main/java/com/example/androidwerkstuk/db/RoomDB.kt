package com.example.androidwerkstuk.db

import android.content.Context
import androidx.room.*
import com.example.androidwerkstuk.converters.LocalDateTimeConverter
import com.example.androidwerkstuk.dao.EventDao
import com.example.androidwerkstuk.dao.UserDao
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.entities.SubscribedUserEventRelation
import com.example.androidwerkstuk.entities.User

@Database(entities = [User::class, Event::class,SubscribedUserEventRelation::class], version = 10, exportSchema = false)
public abstract class RoomDB : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun eventDao(): EventDao


    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context): RoomDB {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "room_db"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}