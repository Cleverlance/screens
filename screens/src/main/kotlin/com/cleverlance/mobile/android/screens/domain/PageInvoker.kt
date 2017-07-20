package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

abstract class PageInvoker<V : BasePresenterView> {
    abstract var viewProvider: ViewProvider<V>

    fun createPage() = PageScreen(viewProvider)
}
