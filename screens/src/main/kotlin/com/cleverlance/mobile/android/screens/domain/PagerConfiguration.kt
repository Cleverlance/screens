package com.cleverlance.mobile.android.screens.domain

class PagerConfiguration(var initPosition: Int = 0, vararg pages: PageScreen) {

    private val tabs = mutableListOf<PageScreen>()

    init {
        for (page in pages)
            registerPage(page)
    }

    internal fun getTab(position: Int) = tabs[position]

    internal fun count() = tabs.size

    internal fun registerPage(page: PageScreen) = run<Unit> { tabs.add(page) }
}