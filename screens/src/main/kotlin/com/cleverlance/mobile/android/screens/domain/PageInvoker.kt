package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import javax.inject.Inject

abstract class PageInvoker<V : BasePresenterView> {
    @Inject internal lateinit var viewProvider: ViewProvider<V>

    fun createPage() = PageScreen(viewProvider)
}
