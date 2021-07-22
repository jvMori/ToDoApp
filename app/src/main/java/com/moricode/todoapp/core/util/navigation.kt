package com.moricode.todoapp.core.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment

import timber.log.Timber
import kotlin.reflect.KClass


fun Fragment.navigate(@IdRes id: Int, block: Bundle.() -> Unit = {}) {
    try {
        val bundle = Bundle().apply(block)
        NavHostFragment.findNavController(this).navigate(id, bundle)
    } catch (e: Exception) {
        Timber.e(e, "Navigate from Fragment = $this")
    }
}

fun Fragment.navigate(navDirections: NavDirections) {
    try {
        NavHostFragment.findNavController(this).navigate(navDirections)
    } catch (e: Exception) {
        Timber.e(e, "Navigate from Fragment = $this")
    }
}

fun Fragment.navigate(@IdRes id: Int, bundle: Bundle) {
    try {
        NavHostFragment.findNavController(this).navigate(id, bundle)
    } catch (e: Exception) {
        Timber.e(e, "Navigate from Fragment = $this")
    }
}

fun Activity.navigate(@IdRes container: Int, @IdRes destination: Int, block: Bundle.() -> Unit = {}) {

    try {
        val bundle = Bundle().apply(block)
        Navigation.findNavController(this, container).navigate(destination, bundle)

    } catch (e: Exception) {
        Timber.e(e, "Navigate from Activity = $this")
    }
}


inline fun <T : Fragment> T.args(block: Bundle.() -> Unit): T {
    arguments = Bundle().apply(block)
    return this
}

inline fun Activity.start(kClass: KClass<*>, request: Int = -1, block: Intent.() -> Unit = {}) {
    val intent = Intent(this, kClass.java).apply(block)
    startActivityForResult(intent, request)
}

inline fun Fragment.start(kClass: KClass<*>, request: Int = -1, block: Intent.() -> Unit = {}) {
    val intent = Intent(context, kClass.java).apply(block)
    startActivityForResult(intent, request)
}



