package com.example.androidwerkstuk.dao

import com.example.androidwerkstuk.entities.User
import kotlinx.coroutines.flow.Flow
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.androidwerkstuk.entities.SubscribedUserEventRelation
import com.example.androidwerkstuk.entities.UserWithEventsOnSubscribed


@Dao
interface UserDao {

    @Query("SELECT * FROM user_table ORDER BY username ASC")
    fun getAllUsers(): LiveData<List<User>>

    @Insert()
    suspend fun insert(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM USER_TABLE WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<User?>?

    @Query("DELETE FROM user_table")
    suspend fun deleteAll()



    @Transaction
    @Query("SELECT * FROM user_table")
    fun userWithEventsOnSubscribed(): List<UserWithEventsOnSubscribed>




}