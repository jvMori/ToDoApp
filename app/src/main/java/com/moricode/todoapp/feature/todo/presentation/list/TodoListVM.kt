package com.moricode.todoapp.feature.todo.presentation.list

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.google.firebase.firestore.FirebaseFirestore
import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseViewModel
import com.moricode.todoapp.core.util.COLLECTION_KEY_NAME
import com.moricode.todoapp.feature.todo.data.FirestorePagingSource

class TodoListVM(
    private val pagingSource: FirestorePagingSource,
) : BaseViewModel() {
    object OnCreateBtnClicked : Actions()
    val adapter = TodoItemAdapter()

    fun createButtonClicked() {
        dispatchAction(OnCreateBtnClicked)
    }

    val flow = Pager(PagingConfig(PAGE_SIZE.toInt())) {
        pagingSource
    }.flow.cachedIn(viewModelScope)
}