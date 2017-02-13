package com.cleverlance.mobile.android.screens.presenter

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext

abstract class BaseAnkoPresenterView : BasePresenterView() {
    override fun createView(container: ViewGroup) = object : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = createAnkoView(ui)
    }.createView(AnkoContext.create(container.context, container))

    abstract fun createAnkoView(ui: AnkoContext<ViewGroup>): View
}