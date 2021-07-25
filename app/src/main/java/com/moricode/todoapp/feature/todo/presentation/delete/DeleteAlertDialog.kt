package com.moricode.todoapp.feature.todo.presentation.delete

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.moricode.todoapp.R

object DeleteAlertDialog {
    fun show(context: Context, callback: (confirm: Boolean) -> Unit) {
        val builder = AlertDialog.Builder(context);
        builder.setMessage(context.getString(R.string.are_you_sure))
        builder.setCancelable(true)

        builder.setPositiveButton(
            context.getString(R.string.yes)
        ) { dialog, _ ->
            callback(true)
            dialog.cancel()
        }

        builder.setNegativeButton(
            context.getString(R.string.no)
        ) { dialog, _ ->
            callback(false)
            dialog.cancel()
        }

        val alert = builder.create()
        alert.show()
    }
}

