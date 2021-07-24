package com.moricode.todoapp.core.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputLayout
import com.moricode.todoapp.R
import java.util.*

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

    @BindingAdapter("dateTime")
    @JvmStatic
    fun formatDate(view: TextView, date: Date?) {
        view.text = date?.formatDate()
    }

    @BindingAdapter("image")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String?) {
        imageView.load(url ?: "") {
            crossfade(true)
            placeholder(R.drawable.ic_baseline_image_24)
            error(R.drawable.ic_baseline_image_24)
            transformations(RoundedCornersTransformation(8f))
        }
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