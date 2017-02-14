package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

class BaseScreen<out V : BasePresenterView>(
        override val back: (Activity) -> Boolean = { false },
        private val viewProvider: ViewProvider<V>) : Screen() {

    override fun presenterView() = viewProvider.createView()
}