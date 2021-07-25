package com.moricode.todoapp.feature.todo.data

import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.feature.todo.domain.NetworkDataSource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import com.moricode.todoapp.feature.todo.domain.mapToHashMap

class TodoRepositoryImpl(
    private val dataSource: NetworkDataSource
) : TodoRepository {

    override suspend fun createTodo(todo: TodoEntity): Resource<Boolean> {
        val data = todo.mapToHashMap()
        return dataSource.create(todo.id, data)
    }

    override suspend fun updateTodo(todo: TodoEntity): Resource<Boolean> {
        val data = todo.mapToHashMap()
        return dataSource.update(todo.id, data)
    }

    override suspend fun deleteTodo(todo: TodoEntity): Resource<Boolean> {
        return dataSource.delete(todo.id)
    }

}