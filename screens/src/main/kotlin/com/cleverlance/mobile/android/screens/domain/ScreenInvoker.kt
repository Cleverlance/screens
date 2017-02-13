package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import javax.inject.Inject

abstract class ScreenInvoker<V : BasePresenterView> {
    @Inject internal lateinit var screenPresenter: ScreenPresenter
    @Inject internal lateinit var viewProvider: ViewProvider<V>

    // TODO rename showScreen()
    open fun createScreen(back: ((Activity) -> Boolean)? = null) = with(screenPresenter) {
        setScreen(BaseScreen(
                back = back ?: screenPresenter.onBackShowPrevious(),
                viewProvider = viewProvider))
    }
}
