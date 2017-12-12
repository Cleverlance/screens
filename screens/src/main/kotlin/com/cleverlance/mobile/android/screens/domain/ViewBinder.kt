package com.cleverlance.mobile.android.screens.domain

import android.view.View
import io.reactivex.disposables.Disposable

internal class ViewBinder(private val binder: () -> Disposable) : View.OnAttachStateChangeListener {
    private lateinit var bindings: Disposable

    override fun onViewAttachedToWindow(v: View) {
        bindings = binder()
    }

    override fun onViewDetachedFromWindow(v: View) {
        bindings.dispose()
    }
}