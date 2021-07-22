package com.moricode.todoapp.feature.todo.presentation.list

import com.moricode.todoapp.core.base.Actions
import com.moricode.todoapp.core.base.BaseViewModel

class TodoListVM : BaseViewModel() {
    object OnCreateBtnClicked : Actions()

    fun createButtonClicked(){
        dispatchAction(OnCreateBtnClicked)
    }
}