package com.example.androidwerkstuk.repositories

import androidx.annotation.WorkerThread
import com.example.androidwerkstuk.entities.User
import androidx.lifecycle.LiveData
import com.example.androidwerkstuk.dao.UserDao
import com.example.androidwerkstuk.entities.Event
import com.example.androidwerkstuk.entities.UserWithEventsOnSubscribed


class UserRepository(private val userDao: UserDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    fun getUserByEmail(email: String): LiveData<User?>? {
        return userDao.getUserByEmail(email)
    }

    fun userWithSubscribedEvents(email : String): LiveData<List<UserWithEventsOnSubscribed>> {
        return userDao.userWithEventsOnSubscribed(email)
    }

    suspend fun addUser(user : User)
    {
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: User){
        userDao.updateUser(user)
    }


    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        userDao.insertUser(user)
    }
}