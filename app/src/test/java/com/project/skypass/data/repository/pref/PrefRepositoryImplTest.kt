package com.project.skypass.data.repository.pref

import com.project.skypass.data.datasource.preference.PrefDataSource
import com.project.skypass.utils.decodeJWT
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.mockkStatic
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PrefRepositoryImplTest {

    @MockK
    lateinit var ds: PrefDataSource
    private lateinit var repo: PrefRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = PrefRepositoryImpl(ds)
    }

    @Test
    fun `isFirstRun returns true`() {
        coEvery { ds.isFirstRun() } returns true

        val result = repo.isFirstRun()

        assertTrue(result)
        coVerify { ds.isFirstRun() }
    }

    @Test
    fun `isFirstRun returns false`() {
        coEvery { ds.isFirstRun() } returns false

        val result = repo.isFirstRun()

        assertFalse(result)
        coVerify { ds.isFirstRun() }
    }

    @Test
    fun setFirstRun() {
        val isFirstRun = true
        coEvery { ds.setFirstRun(isFirstRun) } returns Unit

        repo.setFirstRun(isFirstRun)

        coVerify { ds.setFirstRun(isFirstRun) }
    }

    @Test
    fun `isLogin returns true`() {
        coEvery { ds.isLogin() } returns true

        val result = repo.isLogin()

        assertTrue(result)
        coVerify { ds.isLogin() }
    }

    @Test
    fun `isLogin returns false`() {
        coEvery { ds.isLogin() } returns false

        val result = repo.isLogin()

        assertFalse(result)
        coVerify { ds.isLogin() }
    }

    @Test
    fun setLogin() {
        val isLogin = true
        coEvery { ds.setLogin(isLogin) } returns Unit

        repo.setLogin(isLogin)

        coVerify { ds.setLogin(isLogin) }
    }

    @Test
    fun getToken() {
        val token = "token"
        coEvery { ds.getToken() } returns token

        val result = repo.getToken()

        assertEquals(token, result)
        coVerify { ds.getToken() }
    }

    @Test
    fun setToken() {
        val token = "token"
        coEvery { ds.setToken(token) } returns Unit

        repo.setToken(token)

        coVerify { ds.setToken(token) }
    }

    @Test
    fun clearAll() {
        coEvery { ds.clearAll() } returns Unit

        repo.clearAll()

        coVerify { ds.clearAll() }
    }

    @Test
    fun getUserID() {
        val userID = "user_id"
        coEvery { ds.getUserID() } returns userID

        val result = repo.getUserID()

        assertEquals(userID, result)
        coVerify { ds.getUserID() }
    }

}