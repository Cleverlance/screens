package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

internal class NoScreen : BaseScreen() {
    override fun createView(): BasePresenterView {
        throw Exception("No screen should not be visible at any time.")
    }
}