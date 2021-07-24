package com.moricode.todoapp.feature.todo.data

import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.core.util.saveDataInFireStore
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository

class TodoRepositoryImpl(val db : FirebaseFirestore) : TodoRepository {

    override suspend fun createTodo(todo: TodoEntity): Resource<Boolean> {
        val data = hashMapOf<String, Any?>(
            "id" to todo.id,
            "title" to todo.title,
            "description" to todo.description,
            "iconUrl" to todo.iconUrl,
            "createdAt" to todo.createdAt,
        )
        return db.saveDataInFireStore(todo.title, data)
    }

}