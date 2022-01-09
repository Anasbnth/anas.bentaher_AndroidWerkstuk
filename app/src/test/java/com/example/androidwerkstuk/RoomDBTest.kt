package com.example.androidwerkstuk

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.androidwerkstuk.dao.UserDao
import com.example.androidwerkstuk.db.RoomDB
import com.example.androidwerkstuk.entities.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomDBTest : TestCase() {

    private lateinit var db : RoomDB
    private lateinit var dao: UserDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, RoomDB::class.java).build()
        dao = db.userDao()
    }

    @Test
    fun writeAndReadUser() = runBlocking {
        val user = User("unit@test.com","junitusername","unitTest")
        dao.insertUser(user)

        val dbUser = dao.getUserByEmailForTesting("unit@test.com")

        dao.deleteUserByEmailForTesting(user.email)

        assertEquals(user,dbUser)

    }


    @After
    fun closeDb() {
        db.close()
    }
}