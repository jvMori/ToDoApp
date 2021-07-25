package com.moricode.todoapp.feature.todo.presentation.list
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


const val PAGE_SIZE: Long = 30

val listModule = module {
    viewModel { TodoListVM(db = get(), repository = get()) }
}