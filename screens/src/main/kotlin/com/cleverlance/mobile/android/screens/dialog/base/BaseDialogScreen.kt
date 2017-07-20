package com.cleverlance.mobile.android.screens.dialog.base

import android.app.Activity
import com.cleverlance.mobile.android.screens.dialog.android.BaseDialogScreenView
import com.cleverlance.mobile.android.screens.dialog.android.DialogScreen
import com.cleverlance.mobile.android.screens.dialog.android.DialogScreenView
import javax.inject.Provider

abstract class BaseDialogScreen<T : BaseDialogScreenView> : DialogScreen {
    abstract var viewProvider: Provider<T>
    override fun createDialogScreenView(activity: Activity): DialogScreenView =
            viewProvider.get()
}