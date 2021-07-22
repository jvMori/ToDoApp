package com.moricode.todoapp.feature.todo.domain

import com.moricode.todoapp.core.base.Resource


interface TodoRepository {
    suspend fun createTodo(todo : TodoEntity) : Resource<Boolean>
    suspend fun getTodoList()
}