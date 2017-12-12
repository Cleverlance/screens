package com.cleverlance.mobile.android.screens.presenter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.domain.ViewBinder
import io.reactivex.disposables.Disposable

abstract class BasePresenterView {
    lateinit var activity: Activity
    lateinit var rootView: View

    abstract fun createView(container: ViewGroup): View

    abstract fun bindPresenter(): Disposable
}

internal fun BasePresenterView.createSelfBindingView(container: ViewGroup, activity: Activity): View {
    this.activity = activity
    val view = createView(container)
    view.addOnAttachStateChangeListener(ViewBinder({ bindPresenter() }))
    return view
}