package com.moricode.todoapp.feature.todo.domain



interface TodoRepository {
    suspend fun addTodo(todo : TodoEntity)
    suspend fun getTodoList()
}