package com.moricode.todoapp.feature.todo.data

import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository

class TodoRepositoryImpl : TodoRepository {
    override suspend fun createTodo(todo: TodoEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun getTodoList() {
        TODO("Not yet implemented")
    }

}