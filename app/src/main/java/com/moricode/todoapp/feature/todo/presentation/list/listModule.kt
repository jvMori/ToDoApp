package com.moricode.todoapp.feature.todo.presentation.list

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val listModule = module {
    viewModel { TodoListVM() }
}