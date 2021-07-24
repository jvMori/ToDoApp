package com.moricode.todoapp.core.base

open class Actions {
    override fun toString(): String {
        return this.javaClass.simpleName
    }
    data class Loading(val loading : Boolean) : Actions()
    data class Error(val message : String) : Actions()
    data class Success<T>(val data : T) : Actions()
    object Empty : Actions()
}








