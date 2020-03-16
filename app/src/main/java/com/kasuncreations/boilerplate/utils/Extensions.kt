package com.kasuncreations.boilerplate.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * Show Long Toast message from string resource
 * */
fun Context?.showToastLong(@StringRes textId: Int, duration: Int = Toast.LENGTH_LONG) {
    this?.let {
        Toast.makeText(it, textId, duration).show()
    }

}

/**
 * Show Long Toast message from only String
 * */
fun Context?.showToastLong(text: String, duration: Int = Toast.LENGTH_LONG) {
    this?.let {
        Toast.makeText(it, text, duration).show()
    }
}

/**
 * Start Activity from context
 * */
inline fun <reified T : Activity> Context?.startActivity() =
    this?.startActivity(Intent(this, T::class.java))

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus ?: View(this)
    imm.hideSoftInputFromWindow(view.windowToken, 0)
    window.decorView
}
