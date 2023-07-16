package com.example.dentistmonitor.common.mvp

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import java.util.*

abstract class BaseMvpActivity<V : MvpView, P : MvpPresenter<V>> : AppCompatActivity(), MvpView {

    abstract val presenter: P

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("UNCHECKED_CAST")
        presenter.attach(this as V)
    }

    override fun attachBaseContext(newBase: Context) {
        val configuration = Configuration()
        val newContext = newBase.createConfigurationContext(configuration)
        super.attachBaseContext(newContext)
        @Suppress("DEPRECATION")
        resources.updateConfiguration(
            newContext.resources.configuration,
            newContext.resources.displayMetrics
        )
    }

    @CallSuper
    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }
}