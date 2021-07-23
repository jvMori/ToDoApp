package com.moricode.todoapp.core.util

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout

object Bindings {
    @BindingAdapter("maxCharacters", requireAll = false)
    @JvmStatic
    fun validateOnTextChange(
        textInputLayout: TextInputLayout,
        maxCharacters: Int?
    ) {
        textInputLayout.setErrorIconDrawable(0)
        textInputLayout.editText?.doOnTextChanged { _, _, _, _ ->
            validate(textInputLayout, maxCharacters)
        }
    }

    @BindingAdapter("visibility")
    @JvmStatic
    fun setupVisibility(view: View, visible: Boolean?) {
        view.visibility = if (visible == true) View.VISIBLE else View.INVISIBLE
    }


    fun validate(
        textInputLayout: TextInputLayout,
        maxCharacters: Int?
    ) {
        val text = textInputLayout.editText?.text
        textInputLayout.isErrorEnabled =
            (text.isNullOrEmpty() || text.isMoreThanCharactersLength(maxCharacters))
        textInputLayout.error = if (textInputLayout.isErrorEnabled) {
            if (maxCharacters != null && (text?.isNotEmpty() == true)) "Field cannot be more than $maxCharacters characters long."
            else "This field is required."
        } else {
            ""
        }
    }
}