package com.example.dentistmonitor.auth.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.dentistmonitor.R
import com.example.dentistmonitor.common.extensions.viewbinding.viewBinding
import com.example.dentistmonitor.common.mvp.BaseMvpFragment
import com.example.dentistmonitor.databinding.FragmentAuthBinding
import org.koin.android.ext.android.inject

class AuthFragment :
    BaseMvpFragment<AuthContract.View, AuthContract.Presenter>(
        R.layout.fragment_auth
    ),
    AuthContract.View {

    companion object {
        fun create(): AuthFragment = AuthFragment()
    }

    override val presenter: AuthContract.Presenter by inject()

    private val binding: FragmentAuthBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSignUp.setOnClickListener {
            val name = binding.editTextName.text.toString()
            val password = binding.editTextPassword.text.toString()
            presenter.signUp(name, password)
            presenter.auth()

        }
    }

    override fun showMain() {
//        replaceFragment(.create())
    }

    override fun showError(@StringRes errorResId: Int) {
        Toast.makeText(requireContext(), errorResId, Toast.LENGTH_SHORT).show()
    }
}