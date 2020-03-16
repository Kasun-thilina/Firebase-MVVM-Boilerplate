package com.kasuncreations.boilerplate.utils

import android.text.TextUtils
import android.widget.EditText
import androidx.databinding.BindingAdapter

class BindingAdapter {
    @BindingAdapter("passwordValidator")
    fun passwordValidator(editText: EditText, password: String) {
        val minLength = 5
        if (TextUtils.isEmpty(password)) {
            editText.error = null
            return
        }
        if (editText.text.toString().length < minLength) {
            editText.error = "Password must be minimum $minLength characters length"
        } else {
            editText.error = null
        }
    }
}