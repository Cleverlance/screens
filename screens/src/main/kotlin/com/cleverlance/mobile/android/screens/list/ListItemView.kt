package com.cleverlance.mobile.android.screens.list

import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.Disposable

abstract class ListItemView<P : Any> {
    lateinit var presenter: P

    abstract fun createView(container: ViewGroup): View

    abstract fun bindPresenter(presenter: P): Disposable
}
