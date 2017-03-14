package com.cleverlance.mobile.android.screens.dialog.android

import io.reactivex.disposables.Disposable

/**
 * @param S screen type
 */
interface ScreenDispatcher<S> {
    fun show(screen: S): Disposable
}