package com.example.dentistmonitor.auth.ui

import androidx.annotation.StringRes
import com.example.dentistmonitor.common.mvp.MvpPresenter
import com.example.dentistmonitor.common.mvp.MvpView

interface AuthContract {

    interface View : MvpView {
        fun showMain()
        fun showError(@StringRes errorResId: Int)
    }

    interface Presenter : MvpPresenter<View> {
        fun auth()
        fun signUp(email: String, password: String)
    }
}