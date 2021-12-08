package com.example.androidwerkstuk


import android.content.Context
import android.os.Build.VERSION_CODES.Q
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.androidwerkstuk.dao.UserDao
import com.example.androidwerkstuk.db.RoomDB
import com.example.androidwerkstuk.entities.User
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Q])
class ExampleUnitTest {


    private lateinit var database : RoomDB
    private lateinit var userDAO: UserDao
    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context,RoomDB::class.java).build()
        userDAO = database.userDao()
    }

    @Test
    fun addUserAndReadTest()= runBlocking{
        val user = User("unit@test.com","unitUsername","TestName")



    }

    @After
    fun closeDB(){
        database.close()
    }

}