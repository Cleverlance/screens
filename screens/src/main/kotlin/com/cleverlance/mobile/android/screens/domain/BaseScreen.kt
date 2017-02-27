package com.cleverlance.mobile.android.screens.domain

import io.reactivex.disposables.Disposable

abstract class BaseScreen : Screen() {

    lateinit var dispose: Disposable

    fun onShow(dispose: Disposable) {
        this.dispose = dispose
    }

    /** @return true if back action was consumed by the call */
    open fun onBackPressed(): Boolean {
        dispose.dispose()
        return true
    }
}