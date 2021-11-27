package com.example.androidwerkstuk.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.androidwerkstuk.db.RoomDB
import com.example.androidwerkstuk.entities.User
import com.example.androidwerkstuk.entities.UserWithEventsOnSubscribed
import com.example.androidwerkstuk.repositories.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allUsers: LiveData<List<User>>
    val repository: UserRepository


    init {
        val userDao = RoomDB.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.allUsers
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun addUser(user: User) = viewModelScope.launch {
        repository.insert(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        repository.updateUser(user)
    }


    fun getUserByEmail(email: String) : LiveData<User?>? {
        return repository.getUserByEmail(email)
    }

    fun userWithSubscribedEvents(): List<UserWithEventsOnSubscribed> {
        return repository.userWithSubscribedEvents()
    }




}


