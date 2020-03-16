package com.kasuncreations.boilerplate.presentation.auth

interface AuthListner {
    fun onStarted()
    fun onSuccess()
    fun onError(msg: String)
}