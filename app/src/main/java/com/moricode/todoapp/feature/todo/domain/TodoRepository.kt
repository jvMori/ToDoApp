package com.moricode.todoapp.feature.todo.domain

import com.moricode.todoapp.core.Response

interface TodoRepository {
    suspend fun addTodo(todo : TodoEntity) : Response
    suspend fun getTodoList() : Response
}