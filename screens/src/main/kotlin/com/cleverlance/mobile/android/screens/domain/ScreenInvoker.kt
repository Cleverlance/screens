package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import javax.inject.Inject

abstract class ScreenInvoker {
    @Inject internal lateinit var screenPresenter: ScreenPresenter
    protected abstract val screenFactory: ScreenFactory

    // TODO rename showScreen()
    open fun createScreen(back: ((Activity) -> Boolean)? = null) = with(screenPresenter) {
        setScreen(screenFactory.createScreen(
                back = back ?: screenPresenter.onBackShowPrevious()))
    }
}
