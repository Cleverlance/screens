package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import io.reactivex.disposables.Disposable

abstract class ScreenInvoker {
    abstract val screenPresenter: ScreenPresenter
    abstract val screenFactory: ScreenFactory

    open fun showScreen(dispose: Disposable? = null) = with(screenPresenter) {
        screenFactory.createScreen().let { screen ->
            screen.onShow(dispose ?: screenPresenter.onDisposeShowCurrent())
            setScreen(screen)
        }
    }
}
