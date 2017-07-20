package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import javax.inject.Provider

abstract class ViewProvider<V : BasePresenterView> : ViewFactory {
    abstract var viewProvider: Provider<V>

    override fun createView() = viewProvider.get()!!
}