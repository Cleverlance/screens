package com.cleverlance.mobile.android.screens.dialog

import io.reactivex.disposables.Disposable

interface ScreenFlow<in ScreenType> {
    fun show(screen: ScreenType): Disposable
}
