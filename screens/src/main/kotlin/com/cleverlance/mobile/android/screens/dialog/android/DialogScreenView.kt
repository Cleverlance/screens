package com.cleverlance.mobile.android.screens.dialog.android

import android.app.Activity
import io.reactivex.disposables.Disposable

interface DialogScreenView {
    var activity: Activity

    fun show(): Disposable
}
