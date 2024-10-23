package org.kamilimu.viazilink.farmer.domain.mapper

import org.kamilimu.viazilink.farmer.domain.model.FirestoreError
import java.io.IOException
import java.lang.Exception

/**
 * Maps a Throwable object to a [FirestoreError] object
 */
fun Throwable.toFirestoreError(): FirestoreError {
    return when (this) {
        is IOException -> FirestoreError.NetworkError("Network Error")
        is NullPointerException -> FirestoreError.NoRecordsFound("No Records Found")
        else -> FirestoreError.UnknownError("Unknown Error")
    }
}