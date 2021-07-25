package com.moricode.todoapp.feature.todo.data

import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.core.util.saveDataInFireStore
import com.moricode.todoapp.core.util.update
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import com.moricode.todoapp.feature.todo.domain.mapToHashMap

class TodoRepositoryImpl(val db: FirebaseFirestore) : TodoRepository {

    override suspend fun createTodo(todo: TodoEntity): Resource<Boolean> {
        val data = todo.mapToHashMap()
        return db.saveDataInFireStore(todo.id, data)
    }

    override suspend fun updateTodo(todo: TodoEntity): Resource<Boolean> {
        val data = todo.mapToHashMap()
        return db.update(todo.id, data)
    }

}