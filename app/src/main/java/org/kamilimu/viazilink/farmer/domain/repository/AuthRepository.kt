package org.kamilimu.viazilink.farmer.domain.repository

import org.kamilimu.viazilink.util.AuthStatus

interface AuthRepository {

    suspend fun userLogin(email: String, password: String): AuthStatus

    suspend fun userSignUp(email: String, password: String): AuthStatus

    fun userLogout(): AuthStatus

    fun checkLoginStatus(): AuthStatus

    fun checkEmailValidity(email: String): Boolean
}