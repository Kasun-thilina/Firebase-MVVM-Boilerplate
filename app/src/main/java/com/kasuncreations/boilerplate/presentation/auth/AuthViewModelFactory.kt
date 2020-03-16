package com.kasuncreations.boilerplate.presentation.auth

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasuncreations.boilerplate.data.repository.UserRepository

class AuthViewModelFactory(
    private val repository: UserRepository,
    var application: Application

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository, application) as T
    }

}