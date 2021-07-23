package com.moricode.todoapp.core.base

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FirebaseFirestore> { Firebase.firestore }
    viewModel { MainViewModel() }
}