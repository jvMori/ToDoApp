package com.moricode.todoapp.feature.todo.presentation.list

import com.moricode.todoapp.core.util.COLLECTION_KEY_NAME
import com.moricode.todoapp.feature.todo.data.FirestorePagingSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


const val PAGE_SIZE : Long = 30

val listModule = module {
    single {
        FirestorePagingSource(
            db = get(),
            COLLECTION_KEY_NAME,
            PAGE_SIZE
        )
    }
    viewModel { TodoListVM(get()) }
}