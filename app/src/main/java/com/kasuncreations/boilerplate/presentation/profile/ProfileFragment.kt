package com.kasuncreations.boilerplate.presentation.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kasuncreations.boilerplate.R
import com.kasuncreations.boilerplate.databinding.FragmentProfileBinding
import com.kasuncreations.boilerplate.presentation.auth.AuthViewModel
import com.kasuncreations.boilerplate.presentation.auth.AuthViewModelFactory
import com.kasuncreations.boilerplate.presentation.auth.LoginActivity
import com.kasuncreations.boilerplate.utils.BaseFragment
import com.kasuncreations.boilerplate.utils.dialog.alertDialog
import kotlinx.android.synthetic.main.fragment_profile.view.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class ProfileFragment : BaseFragment(), KodeinAware {
    private val factory: AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel
    override val kodein: Kodein by kodein()

    companion object {
        const val TAG = "profile"
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentProfileBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        val view = binding.root
        viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        view.btn_logout.setOnClickListener {
            //viewModel.logOut()
            this.alertDialog("Logout", getString(R.string.msg_sign_out)) {
                positiveButton("Yes") {
                    viewModel.logOut()
                    val mainIntent = Intent(activity, LoginActivity::class.java)
                    mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    mainIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(mainIntent)
                }
                negativeButton("No") {

                }
            }

        }

        return view
    }


}
