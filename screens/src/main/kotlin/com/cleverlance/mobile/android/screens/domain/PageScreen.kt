package com.cleverlance.mobile.android.screens.domain

class PageScreen(
        private val viewFactory: ViewFactory) : Screen() {
    override fun createView() = viewFactory.createView()
}