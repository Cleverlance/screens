package com.cleverlance.mobile.android.screens.list.view

import android.content.Context
import android.view.View
import com.cleverlance.mobile.android.screens.list.presenter.ListItemPresenter
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class ListItemView<D> {
    val itemSubscription = CompositeDisposable()

    fun holder(context: Context): ListItemViewHolder<D, ListItemView<D>> = ListItemViewHolder(createView(context), this)

    fun setPresenter(presenter: ListItemPresenter<D>) = itemSubscription.addAll(bindPresenter(presenter))

    protected abstract fun bindPresenter(presenter: ListItemPresenter<D>): Disposable

    protected abstract fun createView(context: Context): View
}