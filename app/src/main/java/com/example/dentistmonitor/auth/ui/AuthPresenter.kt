package com.example.dentistmonitor.auth.ui

import com.example.dentistmonitor.R
import com.example.dentistmonitor.auth.interactor.AuthInteractor
import com.example.dentistmonitor.auth.model.AuthState
import com.example.dentistmonitor.common.mvp.BasePresenter
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

class AuthPresenter(
    private val authInteractor: AuthInteractor
) : BasePresenter<AuthContract.View>(),
    AuthContract.Presenter {

    override fun auth() {
        try {
            launch {
                authInteractor.observeAuth()
                    .collectLatest { state -> handleAuthState(state) }
            }
        } catch (e: CancellationException) {
            Timber.e(e.message)
        } catch (t: Throwable) {
            Timber.e(t.message)
        }
    }


    override fun signUp(email: String, password: String) {
        authInteractor.signUp(email, password)
    }

    private fun handleAuthState(state: AuthState) {
        when (state) {
            is AuthState.Success -> {
                authInteractor.saveCredentials(state.credentials)
                view?.showMain()
            }

            is AuthState.Error -> view?.showError(R.string.auth_error)
        }
    }
}