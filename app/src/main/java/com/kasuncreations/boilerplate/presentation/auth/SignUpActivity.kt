package com.kasuncreations.boilerplate.presentation.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kasuncreations.boilerplate.R
import com.kasuncreations.boilerplate.databinding.ActivitySignUpBinding
import com.kasuncreations.boilerplate.utils.BaseActivity
import com.kasuncreations.boilerplate.utils.hideKeyboard
import com.kasuncreations.boilerplate.utils.isValidEmail
import com.kasuncreations.boilerplate.utils.showToastLong
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignUpActivity : BaseActivity(), AuthListner, KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel
    private lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.authListner = this
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.label_sign_up)

    }

    override fun onStart() {
        super.onStart()
    }


    fun onSignUp(button: View) {
        val password = binding.etSignUpUserPassword.text!!.trim()
        val confirmPW = binding.etSignUpUserConfirmPassword.text!!.trim()
        val firstName = binding.etSignUpFirstName.text!!.trim()
        val lastName = binding.etSignUpLastName.text!!.trim()
        val email: String = binding.etSignUpUserEmail.text!!.trim().toString()
        if (firstName.isBlank()) {
            binding.etSignUpFirstName.error = "First Name should not be blank!"
            return
        }
        if (email.isBlank()) {
            binding.etSignUpUserEmail.error = "Email should not be blank!"
            return
        }
        if (email.isNotBlank() && !email.isValidEmail()) {
            binding.etSignUpUserEmail.error = "Please enter a valid email address"
            return
        }
        if (password.isBlank()) {
            binding.etSignUpUserPassword.error = "Password must not be blank"
            return
        }
        if (confirmPW.isBlank()) {
            binding.etSignUpUserConfirmPassword.error = "Please enter your password again"
            return
        }
        if (confirmPW.isNotBlank() && confirmPW != password) {
            binding.etSignUpUserConfirmPassword.error = "Passwords Doesn't Match"
            return
        }
        viewModel.signUp()
    }

    fun hideKeyBoard(layout: View) {
        hideKeyboard()
    }

    override fun onStarted() {
        showProgress()

    }

    override fun onSuccess() {
        hideProgress()
        Intent(this, LoginActivity::class.java).also {
            startActivity(it)
        }
    }

    override fun onError(msg: String) {
        hideProgress()
        showToastLong(msg)
    }

}
