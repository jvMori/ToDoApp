package com.moricode.todoapp.feature.todo.domain

import java.util.*

data class TodoEntity(
    val id : String = "",
    val title : String = "",
    val description : String = "",
    val createdAt : Date? = null,
    val iconUrl : String? = null
)
