package com.moricode.todoapp.feature.todo.presentation.create_new

import com.moricode.todoapp.feature.todo.data.FirebaseDataSource
import com.moricode.todoapp.feature.todo.data.TodoRepositoryImpl
import com.moricode.todoapp.feature.todo.domain.NetworkDataSource
import com.moricode.todoapp.feature.todo.domain.TodoRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val todoModule = module {
    single<NetworkDataSource> { FirebaseDataSource(db = get()) }
    single<TodoRepository> { TodoRepositoryImpl(dataSource = get()) }
    viewModel { TodoCreationVM(repository = get()) }
}