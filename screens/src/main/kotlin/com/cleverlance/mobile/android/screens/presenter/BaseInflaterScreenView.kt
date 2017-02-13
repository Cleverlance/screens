package com.cleverlance.mobile.android.screens.presenter

import android.view.View
import android.view.ViewGroup
import org.jetbrains.anko.layoutInflater

abstract class BaseInflaterScreenView : BasePresenterView() {
    abstract val viewLayoutResource: Int

    override fun createView(container: ViewGroup): View {
        rootView = container.context.layoutInflater.inflate(viewLayoutResource, container, false)
        return rootView
    }
}