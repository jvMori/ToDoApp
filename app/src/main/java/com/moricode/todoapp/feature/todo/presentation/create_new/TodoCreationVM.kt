package com.moricode.todoapp.feature.todo.presentation.create_new

import androidx.lifecycle.MutableLiveData
import com.moricode.todoapp.core.base.BaseViewModel

class TodoCreationVM() : BaseViewModel() {
    val title = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val iconUrl = MutableLiveData<String>()

    fun submit(){
        print(title.value)
        print(description.value)
        print(iconUrl.value)
    }
}