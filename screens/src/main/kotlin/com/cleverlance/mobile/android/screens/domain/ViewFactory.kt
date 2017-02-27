package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

interface ViewFactory {
    fun createView(): BasePresenterView
}