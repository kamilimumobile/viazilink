package org.kamilimu.viazilink.farmer.data.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import kotlinx.coroutines.tasks.await
import org.kamilimu.viazilink.farmer.domain.repository.AuthRepository
import org.kamilimu.viazilink.util.AuthStatus
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthRepository {
    /**
     * Invoked when a registered user logs in
     */
    override suspend fun userLogin(email: String, password: String): AuthStatus {
        return try {
            // Validate the email pattern
            if (!checkEmailValidity(email))
                return AuthStatus.Error("Use a valid email pattern")

            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            AuthStatus.Authenticated
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            AuthStatus.Error("Invalid email or password")
        } catch (e: Exception) {
            AuthStatus.Error(e.message ?: "Something went wrong")
        }
    }

    /**
     * Invoked when a new user registers an account
     */
    override suspend fun userSignUp(email: String, password: String): AuthStatus {
        return try {
            // Validate the email pattern
            if (!checkEmailValidity(email))
                return AuthStatus.Error("Use a valid email pattern")
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            AuthStatus.Authenticated
        } catch (e: FirebaseAuthWeakPasswordException) {
            AuthStatus.Error("Password must contain at least 6 characters")
        } catch (e: Exception) {
            AuthStatus.Error(e.message ?: "Something went wrong")
        }
    }

    /**
     * Invoked when a registered user logs out from the app
     */
    override fun userLogout(): AuthStatus {
        firebaseAuth.signOut()
        return AuthStatus.Unauthenticated
    }

    /**
     * Used to check whether or not a user is currently logged in on the app
     */
    override fun checkLoginStatus(): AuthStatus {
        return if (firebaseAuth.currentUser == null)
            AuthStatus.Unauthenticated
        else
            AuthStatus.Authenticated
    }

    /**
     * Checks if the email has a valid pattern
     */
    override fun checkEmailValidity(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()
        return email.matches(emailRegex)
    }
}