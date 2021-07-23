package com.moricode.todoapp.core.util

import android.widget.Toast
import androidx.fragment.app.Fragment


fun Fragment.makeToast(message: String) {
    Toast.makeText(
        context, message,
        Toast.LENGTH_SHORT
    ).show()
}