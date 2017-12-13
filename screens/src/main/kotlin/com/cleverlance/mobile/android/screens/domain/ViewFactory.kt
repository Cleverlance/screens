package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.cleverlance.mobile.android.screens.presenter.createSelfBindingView

interface ViewFactory {
    fun createView(): BasePresenterView?
}

internal fun ViewFactory.createSelfBindingView(viewGroup: ViewGroup, activity: Activity): View? =
        createView()?.createSelfBindingView(viewGroup, activity)
