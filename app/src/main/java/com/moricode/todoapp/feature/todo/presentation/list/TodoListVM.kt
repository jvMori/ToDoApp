package com.moricode.todoapp.feature.todo.presentation.list

import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseViewModel
import com.moricode.todoapp.core.util.COLLECTION_KEY_NAME
import com.moricode.todoapp.feature.todo.data.FirestorePagingSource
import com.moricode.todoapp.feature.todo.domain.TodoEntity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class TodoListVM(
    private val pagingSource: FirestorePagingSource,
) : BaseViewModel() {

    object OnCreateBtnClicked : Actions()
    data class OnItemClicked(val entity: TodoEntity?) : Actions()

    val adapter = TodoItemAdapter(){
        dispatchAction(OnItemClicked(it))
    }

    fun createButtonClicked() {
        dispatchAction(OnCreateBtnClicked)
    }

    val flow = Pager(PagingConfig(PAGE_SIZE.toInt())) {
        pagingSource
    }.flow.cachedIn(viewModelScope)

    fun handleLoadingStates(){
        adapter.withLoadStateFooter(
            footer = MyLoadStateAdapter(adapter::retry)
        )
        viewModelScope.launch {
            adapter.loadStateFlow.collectLatest { loadStates ->
                setIsLoading(loadStates.refresh is LoadState.Loading)
                setIsError(loadStates.refresh is LoadState.Error)
                setIsLoading(loadStates.append is LoadState.Loading)
                //retry.isVisible = loadState.refresh !is LoadState.Loading
            }
        }
    }
}