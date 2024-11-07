package org.kamilimu.viazilink

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.kamilimu.viazilink.farmer.data.repository.AuthRepositoryImpl
import org.kamilimu.viazilink.util.AuthStatus
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class AuthTests {

    @Mock
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authRepository: AuthRepository

    @Before
    fun setUp() {
        authRepository = AuthRepositoryImpl(firebaseAuth = firebaseAuth)
    }

    @Test
    fun emailFormat_isCorrect() {
        assertTrue(authRepository.checkEmailValidity("test@example.org"))
    }

    @Test
    fun `email has an incorrect format`() {
        assertEquals(authRepository.checkEmailValidity("bad-email"), false)
    }

    @Test
    fun userLoginWithValidEmail_ReturnsAuthenticated() = runBlocking {
        val email = "test@example.com"
        val password = "password123"

        val mockTask = mock<Task<AuthResult>>()
        whenever(firebaseAuth.signInWithEmailAndPassword(email, password)).thenReturn(mockTask)
        whenever(mockTask.await()).thenReturn(mock())

        val result = authRepository.userLogin(email, password)
        assertEquals(AuthStatus.Authenticated, result)
    }
}