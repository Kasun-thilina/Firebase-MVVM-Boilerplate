package com.kasuncreations.boilerplate.presentation.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kasuncreations.boilerplate.data.repository.UserRepository


class HomeViewModelFactory(
    private val repository: UserRepository,
    var application: Application

) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(repository, application) as T
    }

}