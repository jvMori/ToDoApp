package com.moricode.todoapp.feature.todo.presentation.create_new

import com.moricode.todoapp.feature.todo.data.TodoRepositoryImpl
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoCreationModule = module {
    single<TodoRepository> { TodoRepositoryImpl(db = get()) }
    viewModel { TodoCreationVM(repository = get()) }
}