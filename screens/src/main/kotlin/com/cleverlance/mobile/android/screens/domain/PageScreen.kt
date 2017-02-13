package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

class PageScreen<out V : BasePresenterView>(
        private val viewProvider: ViewProvider<V>) : Screen() {

    override fun presenterView() = viewProvider.createView()
}