package com.cleverlance.mobile.android.screens.dialog.base

import android.support.annotation.CheckResult
import com.cleverlance.mobile.android.screens.dialog.DialogResultCallback
import com.cleverlance.mobile.android.screens.dialog.GenericDialogInvokeHelper
import com.cleverlance.mobile.android.screens.dialog.ScreenFactory
import com.cleverlance.mobile.android.screens.dialog.android.DialogScreen
import io.reactivex.Single

abstract class BaseDialogInvoker<in D, R : DialogResult> {
    abstract var dialogInvokerHelper: GenericDialogInvokeHelper<DialogScreen>

    @CheckResult
    fun request(data: D): Single<R> {
        return dialogInvokerHelper.forResult(object : ScreenFactory<R, DialogScreen> {
            override fun create(dialogResultCallback: DialogResultCallback<R>): DialogScreen {
                return createScreen(data, dialogResultCallback)
            }
        })
    }

    abstract fun createScreen(data: D, dialogResultCallback: DialogResultCallback<R>): DialogScreen
}
