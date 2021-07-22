package com.moricode.todoapp.feature.todo.domain

import com.google.type.DateTime
import java.util.*

data class TodoEntity(
    val id : String,
    val title : String,
    val description : String,
    val createdAt : Date,
    val iconUrl : String?
)
