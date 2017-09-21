package com.cleverlance.mobile.android.screens.presenter

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoContext

abstract class BaseAnkoPresenterView : BasePresenterView() {
    override fun createView(container: ViewGroup) =
            createAnkoView(AnkoContext.create(container.context, container))

    abstract fun createAnkoView(ui: AnkoContext<ViewGroup>): View
}