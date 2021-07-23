package com.moricode.todoapp.core.util

import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

object Bindings {
    @BindingAdapter("maxCharacters", requireAll = false)
    @JvmStatic
    fun validateTextInputLayout(
        textInputLayout: TextInputLayout,
        maxCharacters: Int?
    ) {
        textInputLayout.setErrorIconDrawable(0)
        textInputLayout.editText?.doOnTextChanged { text, _, _, _ ->
            textInputLayout.isErrorEnabled =
                (text == null || text.isEmpty() || text.length > (maxCharacters
                    ?: 0))
            textInputLayout.error = if (textInputLayout.isErrorEnabled) {
                if (maxCharacters != null) "Field cannot be more than $maxCharacters characters length."
                else "This field is required."
            } else {
                ""
            }
        }
    }
}