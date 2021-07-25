package com.moricode.todoapp.feature.todo.domain

import com.moricode.todoapp.core.base.Resource

interface NetworkDataSource {
    suspend fun create(childName: String, data: HashMap<String, Any?>): Resource<Boolean>
    suspend fun update(id: String, data: HashMap<String, Any?>): Resource<Boolean>
    suspend fun delete(id: String): Resource<Boolean>
}