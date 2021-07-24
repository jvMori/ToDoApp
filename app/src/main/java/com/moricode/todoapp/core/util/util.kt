package com.moricode.todoapp.core.util

import android.widget.Toast
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*


fun Fragment.makeToast(message: String) {
    Toast.makeText(
        context, message,
        Toast.LENGTH_SHORT
    ).show()
}

fun Date.formatDate(): String {
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH)
    return formatter.format(this)
}
