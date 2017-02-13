package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

class PagerConfiguration<V : BasePresenterView>(var initPosition: Int = 0, vararg pages: PageScreen<V>) {

    private val tabs = mutableListOf<PageScreen<V>>()

    init {
        for (page in pages)
            registerPage(page)
    }

    internal fun getTab(position: Int) = tabs[position]

    internal fun count() = tabs.size

    internal fun registerPage(page: PageScreen<V>) = run<Unit> { tabs.add(page) }
}