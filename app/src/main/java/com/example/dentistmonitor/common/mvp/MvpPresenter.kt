package com.example.dentistmonitor.common.mvp

interface MvpPresenter<V : MvpView> {

    fun attach(view: V)

    fun detach()
}