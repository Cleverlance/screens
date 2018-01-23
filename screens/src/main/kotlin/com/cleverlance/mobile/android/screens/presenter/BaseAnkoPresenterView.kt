package com.cleverlance.mobile.android.screens.presenter

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

abstract class BaseAnkoPresenterView : BasePresenterView(), AnkoComponent<ViewGroup> {
    override fun createView(container: ViewGroup) =
            createView(AnkoContext.create(container.context, container))

    override fun createView(ui: AnkoContext<ViewGroup>): View = createAnkoView(ui)

    abstract fun createAnkoView(ui: AnkoContext<ViewGroup>): View
}