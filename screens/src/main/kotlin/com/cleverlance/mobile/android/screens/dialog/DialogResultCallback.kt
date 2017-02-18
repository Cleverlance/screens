package com.cleverlance.mobile.android.screens.dialog

interface DialogResultCallback<R> {
    fun dismissWithResult(result: R)

    fun dismissWithError(error: Throwable)
}
