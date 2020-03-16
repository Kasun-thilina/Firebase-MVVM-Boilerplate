package com.kasuncreations.boilerplate.presentation.auth

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.kasuncreations.boilerplate.data.repository.UserRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by Kasun Thilina on 26 February 2020
 * kasun.thilina.t@gmail.com
 *
 * ViewModel for auth
 *  - using RxJava Disposable
 *  - using Livedata
 *  - using ViewBinding
 */
class AuthViewModel(
    val repository: UserRepository,
    application: Application

) : ViewModel() {

    var email: String? = null
    var password: String? = null
    var firstName: String? = null
    var lastName: String? = null

    var authListner: AuthListner? = null

    private val disposables = CompositeDisposable()


    val user by lazy {
        repository.getCurrentUser()
    }

    fun login() {
        //validation
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            authListner!!.onError("Please try again. Invalid Email or Password!")
            return
        }

        authListner?.onStarted()

        val disposable = repository.login(email!!, password!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    authListner?.onSuccess()
                },
                {
                    authListner?.onError(it.message!!)
                }
            )

        disposables.add(disposable)
    }

    fun signUp() {
        authListner?.onStarted()
        val disposable = repository.signUp(email!!, password!!, firstName!!, lastName!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    authListner?.onSuccess()
                },
                {
                    authListner?.onError(it.message!!)
                }
            )
        disposables.add(disposable)
    }

    fun goToSignUp(view: View) {
        Intent(view.context, SignUpActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun goToLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)
        }
    }

    fun logOut() {
        repository.logout()
    }

    fun validation(): Boolean {
        val isEmail = email
        return true
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}