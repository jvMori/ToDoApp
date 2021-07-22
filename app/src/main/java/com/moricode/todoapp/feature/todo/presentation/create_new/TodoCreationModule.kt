package com.moricode.todoapp.feature.todo.presentation.create_new

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoCreationModule = module {
    viewModel { TodoCreationVM() }
}