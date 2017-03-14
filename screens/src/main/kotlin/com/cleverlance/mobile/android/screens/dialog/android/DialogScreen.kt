package com.cleverlance.mobile.android.screens.dialog.android

import android.app.Activity

interface DialogScreen {
    fun createDialogScreenView(activity: Activity): DialogScreenView
}
