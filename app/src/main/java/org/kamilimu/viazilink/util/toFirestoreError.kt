package org.kamilimu.viazilink.util

import org.kamilimu.viazilink.client.domain.model.FirestoreError

// Convert Throwable to FirestoreError
fun Throwable.toFirestoreError(): FirestoreError {
    return when (this) {
        is NullPointerException -> FirestoreError.NotFound
        else -> FirestoreError.GenericError(this.message ?: "Unknown Firestore error")
    }
}

// Convert FirestoreError to Throwable
fun FirestoreError.toThrowable(): Throwable {
    // Using a custom Exception type or just Exception
    return Exception(this.getMessage())
}
