package com.project.skypass.data.datasource.preference

import com.project.skypass.data.source.local.pref.UserPreference
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PrefDataSourceImplTest {
    @MockK
    lateinit var pref: UserPreference
    private lateinit var ds: PrefDataSource

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        ds = PrefDataSourceImpl(pref)
    }

    @Test
    fun isFirstRun() {
        every { pref.isFirstRun() } returns true
        val result = ds.isFirstRun()
        assertEquals(true, result)
        verify { pref.isFirstRun() }
    }

    @Test
    fun setFirstRun() {
        every { pref.setFirstRun(any()) } just runs
        ds.setFirstRun(true)
        verify { pref.setFirstRun(true) }
    }

    @Test
    fun isLogin() {
        every { pref.isLogin() } returns true
        val result = ds.isLogin()
        assertEquals(true, result)
        verify { pref.isLogin() }
    }

    @Test
    fun setLogin() {
        every { pref.setLogin(any()) } just runs
        ds.setLogin(true)
        verify { pref.setLogin(true) }
    }

    @Test
    fun getUserID() {
        val expectedUserID = "user123"
        every { pref.getUserID() } returns expectedUserID
        val result = ds.getUserID()
        assertEquals(expectedUserID, result)
        verify { pref.getUserID() }
    }

    @Test
    fun setUserID() {
        every { pref.setUserID(any()) } just runs
        val userID = "user123"
        ds.setUserID(userID)
        verify { pref.setUserID(userID) }
    }

    @Test
    fun getToken() {
        val expectedToken = "token123"
        every { pref.getToken() } returns expectedToken
        val result = ds.getToken()
        assertEquals(expectedToken, result)
        verify { pref.getToken() }
    }

    @Test
    fun setToken() {
        every { pref.setToken(any()) } just runs
        val token = "token123"
        ds.setToken(token)
        verify { pref.setToken(token) }
    }

    @Test
    fun clearAll() {
        every { pref.clearAll() } just runs
        ds.clearAll()
        verify { pref.clearAll() }
    }
}
