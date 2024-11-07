package org.kamilimu.viazilink.client.domain.model

sealed class FirestoreError {
    object NotFound : FirestoreError()
    data class GenericError(val message: String) : FirestoreError()

    // Method to get the error message
    fun getMessage(): String {
        return when (this) {
            is NotFound -> "Resource not found"
            is GenericError -> message
        }
    }
}
