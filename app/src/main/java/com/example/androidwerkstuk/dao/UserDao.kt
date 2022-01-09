package com.example.androidwerkstuk.dao

import android.os.FileObserver.DELETE
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

    @Query("SELECT * FROM user_table ORDER BY username ASC")
    suspend fun getAllUsersTest(): List<User>?

    @Insert()
    suspend fun insertUser(user: User)

    @Insert()
    suspend fun insertUserWithEventsOnSubscribed(ref : List<SubscribedUserEventRelation>)


    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT * FROM USER_TABLE WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<User?>?

    @Query("SELECT * FROM USER_TABLE WHERE email = :email")
    fun getUserByEmailForTesting(email: String): User



    @Query("DELETE FROM user_table")
    suspend fun deleteAll()

    @Query("DELETE FROM user_table where email =  :email")
    suspend fun deleteUserByEmailForTesting(email: String)



    @Transaction
    @Query("SELECT * FROM user_table WHERE email = :email")
    fun userWithEventsOnSubscribed(email : String): LiveData<List<UserWithEventsOnSubscribed>>




}