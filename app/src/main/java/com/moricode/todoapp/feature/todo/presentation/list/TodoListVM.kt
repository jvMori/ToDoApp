package com.moricode.todoapp.feature.todo.presentation.list

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseViewModel
import com.moricode.todoapp.feature.todo.data.COLLECTION_KEY_NAME
import com.moricode.todoapp.feature.todo.data.FirestorePagingSource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodoListVM(
    private val db : FirebaseFirestore,
    private val repository: TodoRepository
) : BaseViewModel() {

    object OnCreateBtnClicked : Actions()
    data class OnItemClicked(val entity: TodoEntity?) : Actions()
    data class OnLongClick(val entity: TodoEntity?) : Actions()

    val adapter = TodoItemAdapter(
        onClick = {
            dispatchAction(OnItemClicked(it))
        },
        onLongClick = {
            dispatchAction(OnLongClick(it))
        },
    )

    fun createButtonClicked() {
        dispatchAction(OnCreateBtnClicked)
    }

    fun listenForChanges() {
        viewModelScope.launch {
            repository.listenForChanges() {
               // pagingSource.invalidate()
                adapter.refresh()
            }
        }
    }

    fun deleteTodo(todoEntity: TodoEntity?) {
        viewModelScope.launch {
            todoEntity?.let {
                repository.deleteTodo(it)
            }
        }
    }

    val flow = Pager(PagingConfig(PAGE_SIZE.toInt())) {
        FirestorePagingSource(
            db,
            COLLECTION_KEY_NAME,
            PAGE_SIZE
        )
    }.flow.cachedIn(viewModelScope)


    fun handleLoadingStates() {
        adapter.withLoadStateFooter(
            footer = MyLoadStateAdapter(adapter::retry)
        )
        viewModelScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                setIsLoading(loadStates.refresh is LoadState.Loading)
                setIsError(loadStates.refresh is LoadState.Error)
                setIsLoading(loadStates.append is LoadState.Loading)
            }
        }
    }
}