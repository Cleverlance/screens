package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

abstract class Screen : ViewFactory {
    // TODO creating is ineffective
    fun title() = createView().title()

    @Deprecated("Renamed to createView()", ReplaceWith("createView()"))
    fun presenterView(): BasePresenterView = createView()
}