package com.cleverlance.mobile.android.screens.list.view

import android.support.v7.widget.RecyclerView
import android.view.View
import io.reactivex.disposables.Disposables

class ListItemViewHolder<out V : ListItemView<P>, P : Any>(
        view: View,
        val listItem: V
) : RecyclerView.ViewHolder(view) {
    private var bindings = Disposables.disposed()

    internal fun bind() {
        bindings = listItem.bindPresenter(listItem.presenter)
    }

    internal fun unbind() = bindings.dispose()
}