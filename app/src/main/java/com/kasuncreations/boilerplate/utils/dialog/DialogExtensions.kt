package com.kasuncreations.boilerplate.utils.dialog

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Extension for custom dialog
 * @author kasun.thilina.t@gmail.com
 * @since 16th March 2020
 */
const val TAG = "dialog"

inline fun Activity.alertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    func: DialogHelper.() -> Unit
) {
    val dialogFragment = DialogHelper(this, title, message).apply {
        func()
    }
    val fragmentTransaction = (this as AppCompatActivity).supportFragmentManager.beginTransaction()
    fragmentTransaction.let { dialogFragment.show(it, TAG) }
}

inline fun Fragment.alertDialog(
    title: CharSequence? = null,
    message: CharSequence? = null,
    func: DialogHelper.() -> Unit
) {
    val dialogFragment = DialogHelper(this.context!!, title, message).apply {
        func()
    }
    val fragmentTransaction = (this).childFragmentManager.beginTransaction()
    fragmentTransaction.let { dialogFragment.show(it, TAG) }
}

