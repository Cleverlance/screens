package com.cleverlance.mobile.android.screens.list.presenter

import com.jakewharton.rxrelay2.BehaviorRelay

open class ListPresenterHolder<D> {
    internal val presenters = arrayListOf<ListItemPresenter<D>>()
    val itemSelected = BehaviorRelay.create<D>()

    fun createPresenters(items: List<D>) {
        presenters.clear()
        items.mapTo(presenters) { ListItemPresenter(it) }
    }
}