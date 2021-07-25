package com.moricode.todoapp.feature.todo.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


@Parcelize
data class TodoEntity(
    val id : String = "",
    val title : String = "",
    val description : String = "",
    val createdAt : Date? = null,
    val iconUrl : String? = null
) : Parcelable

fun TodoEntity.mapToHashMap() : HashMap<String, Any?>{
    return hashMapOf<String, Any?>(
        "id" to this.id,
        "title" to this.title,
        "description" to this.description,
        "iconUrl" to this.iconUrl,
        "createdAt" to this.createdAt,
    )
}