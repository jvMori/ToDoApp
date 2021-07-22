package com.moricode.todoapp.core.base

import androidx.lifecycle.*
import kotlinx.coroutines.*
import timber.log.Timber



abstract class BaseViewModel : ViewModel() {

    val actions = MutableLiveData<Actions>()
    val loadingState = MutableLiveData<Boolean>()
    val loadingStateUpload = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<Boolean>()


    fun setIsLoading(loading: Boolean) {
        viewModelScope.launch {
            loadingState.value = loading
            errorState.value = false
        }
    }

    fun dispatchAction(action: Actions) {
        Timber.d("Sending $action")
        viewModelScope.launch {
            actions.value = action
        }
    }
}

