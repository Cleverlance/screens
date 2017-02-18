package com.cleverlance.mobile.android.screens.dialog

import io.reactivex.disposables.Disposable

interface ScreenFlow<ScreenType> {
    fun show(screen: ScreenType, dialogResultCallback: DialogResultCallback<*>): Disposable
}
