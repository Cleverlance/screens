package com.cleverlance.mobile.android.screens.dialog.android

import android.app.Activity
import android.support.annotation.MainThread
import io.reactivex.disposables.Disposable

/** Creates dialogView view based on provided screen and shows it  */
class DialogScreenViewContainer private constructor(private val activity: Activity) : ScreenDispatcher<DialogScreen> {
    private var dismissDialog: Disposable? = null

    @MainThread
    override fun show(screen: DialogScreen) {
        check(dismissDialog == null) { "Previous dialog has not been dismissed yet: $dismissDialog" }

        val dialogView = screen.createDialogScreenView(activity)
        dismissDialog = dialogView.show()
    }

    @MainThread
    override fun hide() {
        if (dismissDialog != null) {
            dismissDialog!!.dispose()
        }
        dismissDialog = null
    }

    companion object {
        fun of(activity: Activity): DialogScreenViewContainer {
            return DialogScreenViewContainer(activity)
        }
    }
}
