package com.moricode.todoapp.core.base

import androidx.annotation.Nullable
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import java.net.SocketException

class Resource<T>(
    val status: Status?,
    val data: T?,
    val message: String?,
    val code: Int?
) {

    sealed class Status {
        object SUCCESS : Status()
        object ERROR : Status()
    }

    companion object {
        fun <T> success(@Nullable data: T): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(
            @Nullable msg: String,
            @Nullable data: T? = null,
            @Nullable code: Int? = null
        ): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
                code
            )
        }

    }
}

fun <T> handleError(e: Exception): Resource<T> {
    return if (e is SocketException) {
        Resource.error<T>("No internet connection!");
    } else
        Resource.error(e.message ?: "Something went wrong");
}

fun handleFirestoreErrorMessage(loadStates: CombinedLoadStates): String? {
    return if ((loadStates.refresh as LoadState.Error).error is ArrayIndexOutOfBoundsException) {
        "No items found!"
    } else
        (loadStates.refresh as LoadState.Error).error.localizedMessage

}
