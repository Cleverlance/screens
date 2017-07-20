package com.cleverlance.mobile.android.screens.list.view

import android.support.v7.widget.RecyclerView
import com.cleverlance.mobile.android.screens.list.presenter.ListPresenterHolder

abstract class ListAdapter<
        D,
        PH : ListPresenterHolder<D>,
        LI : ListItemViewHolder<D, ListItemView<D>>>(
        private val presenterHolder: PH
) : RecyclerView.Adapter<LI>() {

    var items: List<D> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: LI, position: Int) {
        holder.listItem.setPresenter(presenterHolder.presenters[position])
    }

    override fun getItemCount(): Int = items.size

    override fun onViewAttachedToWindow(holder: LI) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: LI) {
        holder.unbind()
        super.onViewDetachedFromWindow(holder)
    }
}