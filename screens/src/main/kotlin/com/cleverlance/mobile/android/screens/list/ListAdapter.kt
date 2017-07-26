package com.cleverlance.mobile.android.screens.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

abstract class ListAdapter<
        V : ListItemView<P>,
        P : Any> : RecyclerView.Adapter<ListItemViewHolder<V, P>>() {
    override fun onCreateViewHolder(container: ViewGroup, viewType: Int): ListItemViewHolder<V, P> {
        val itemView = createView(viewType)
        return ListItemViewHolder(itemView.createView(container), itemView)
    }

    abstract fun createView(viewType: Int): V

    override fun onBindViewHolder(holder: ListItemViewHolder<V, P>, position: Int) {
        holder.listItem.presenter = getPresenter(position)
    }

    abstract fun getPresenter(position: Int): P

    override fun onViewAttachedToWindow(holder: ListItemViewHolder<V, P>) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: ListItemViewHolder<V, P>) {
        holder.unbind()
        super.onViewDetachedFromWindow(holder)
    }
}