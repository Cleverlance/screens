package com.cleverlance.mobile.android.screens.dialog.android

import io.reactivex.disposables.Disposable

interface DialogScreenView {
    fun show(): Disposable
}
