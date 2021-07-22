package com.moricode.todoapp.feature.todo.presentation.create_new

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.type.DateTime
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseViewModel
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class TodoCreationVM(val repository: TodoRepository) : BaseViewModel() {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val iconUrl = MutableLiveData<String>()

    fun submit() {
        viewModelScope.launch {
            val date = Calendar.getInstance().time
            val entity = TodoEntity(
                id = System.currentTimeMillis().toString(),
                title = title.value ?: "",
                description = description.value ?: "",
                iconUrl = iconUrl.value,
                createdAt = date,
            )
            val result = repository.createTodo(entity)
            when (result.status) {
                is Resource.Status.SUCCESS -> {
                    dispatchAction(Actions.Success(true))
                }
                else -> {
                }
            }
        }
    }
}