package com.kasuncreations.boilerplate.presentation.profile

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.kasuncreations.boilerplate.data.repository.UserRepository
import com.kasuncreations.boilerplate.presentation.auth.LoginActivity

class ProfileViewModel(
    private val repository: UserRepository
) : ViewModel() {

    val user by lazy {
        repository.getCurrentUser()
    }

    fun logout(view: View) {
        repository.logout()
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }
}