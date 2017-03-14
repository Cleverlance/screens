package com.cleverlance.mobile.android.screens.dialog.android

/**
 * @param S screen type
 */
interface ScreenDispatcher<S> {
    fun show(screen: S)

    fun hide()
}