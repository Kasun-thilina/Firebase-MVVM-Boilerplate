package com.kasuncreations.boilerplate.data.repository

import com.kasuncreations.boilerplate.data.firebase.FirebaseFunctions

/**
 * Created by Kasun Thilina on 26 February 2020
 * kasun.thilina.t@gmail.com
 *
 * User repository
 */
class UserRepository(
    private val firebaseFunc: FirebaseFunctions
) {

    fun login(email: String, password: String) = firebaseFunc.login(email, password)

    fun signUp(email: String, password: String, firstName: String, lastName: String) =
        firebaseFunc.signUp(email, password, firstName, lastName)

    fun getCurrentUser() = firebaseFunc.currentUser()

    fun logout() = firebaseFunc.signOut()
}