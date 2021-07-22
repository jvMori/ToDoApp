package com.moricode.todoapp.core.base

import android.app.Application
import com.moricode.todoapp.feature.todo.presentation.create_new.todoCreationModule
import com.moricode.todoapp.feature.todo.presentation.list.listModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //GlobalContext.application = this
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidLogger()
            androidContext(this@BaseApplication)
            modules(
                listOf(
                    listModule,
                    todoCreationModule,
                )
            )
        }
    }
}
