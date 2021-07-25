package com.moricode.todoapp.feature.todo.presentation.create_new

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseViewModel
import com.moricode.todoapp.core.base.Resource
import com.moricode.todoapp.core.util.isMoreThanCharactersLength
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import kotlinx.coroutines.launch
import java.util.*

class TodoCreationVM(val repository: TodoRepository) : BaseViewModel() {
    object Validate : Actions()

    val titleMaxChar = 30
    val descriptionMaxChar = 200

    var todoEntity: TodoEntity? = null

    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val iconUrl = MutableLiveData<String>()

    fun init(todoEntity: TodoEntity?) {
        this.todoEntity = todoEntity
        todoEntity?.let {
            title.value = it.title
            description.value = it.description
            iconUrl.value = it.iconUrl ?: ""
        }
    }

    fun submit() {
        dispatchAction(Validate)
        if (title.value.isNullOrEmpty() ||
            title.value.isMoreThanCharactersLength(titleMaxChar) ||
            description.value.isNullOrEmpty() ||
            description.value.isMoreThanCharactersLength(descriptionMaxChar)
        ) {
            dispatchAction(Actions.Error("Please provide correct data before continuing."))
            return
        }
        setIsLoading(true)
        viewModelScope.launch {
            val result =
                if (todoEntity != null) {
                    val data = createEntity(todoEntity!!.id)
                    repository.updateTodo(data)
                } else {
                    val data = createEntity(null)
                    repository.createTodo(data)
                }
            when (result.status) {
                is Resource.Status.SUCCESS -> {
                    resetValues()
                    dispatchAction(Actions.Success(true))
                }
                is Resource.Status.ERROR -> {
                    dispatchAction(Actions.Error(result.message ?: "Something went wrong!"))
                }
            }
        }
        setIsLoading(false)
    }

    private fun createEntity(id: String?) = TodoEntity(
        id = id ?: System.currentTimeMillis().toString(),
        title = title.value ?: "",
        description = description.value ?: "",
        iconUrl = iconUrl.value,
        createdAt = Calendar.getInstance().time,
    )

    private fun resetValues() {
        title.value = ""
        description.value = ""
        iconUrl.value = ""
    }
}