package com.moricode.todoapp.feature.todo.domain

import com.google.type.DateTime

data class TodoEntity(
    val id : String,
    val title : String,
    val description : String,
    val createdAt : DateTime,
    val iconUrl : String?
)
