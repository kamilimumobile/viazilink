package org.kamilimu.viazilink.farmer.domain.model

sealed class FirestoreError(val error: String) {
    data class NoRecordsFound(val message: String): FirestoreError(message)
    data class NetworkError(val message: String): FirestoreError(message)
    data class UnknownError(val message: String): FirestoreError(message)
}