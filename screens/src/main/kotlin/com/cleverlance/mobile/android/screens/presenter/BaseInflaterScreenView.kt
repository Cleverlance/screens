package com.cleverlance.mobile.android.screens.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseInflaterScreenView : BasePresenterView() {
    abstract val viewLayoutResource: Int

    override fun createView(container: ViewGroup): View {
        rootView = container.context.layoutInflater.inflate(viewLayoutResource, container, false)
        return rootView
    }

    private val Context.layoutInflater: LayoutInflater
        get() = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
}
