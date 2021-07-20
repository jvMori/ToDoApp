package com.moricode.todoapp.feature.todo.domain

import com.google.type.DateTime

data class TodoEntity(
    val title : String,
    val description : String,
    val createdAt : DateTime,
    val iconUrl : String?
)
