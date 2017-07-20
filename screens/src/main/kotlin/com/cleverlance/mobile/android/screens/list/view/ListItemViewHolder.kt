package com.cleverlance.mobile.android.screens.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.disposables.CompositeDisposable

class ListItemViewHolder<D, out LI : ListItemView<D>>(
        val view: View,
        val listItem: LI
) : RecyclerView.ViewHolder(view) {
    private val onBindSubscriptions = CompositeDisposable()

    fun bind() = onBindSubscriptions.addAll(listItem.itemSubscription)

    fun unbind() = onBindSubscriptions.dispose()
}