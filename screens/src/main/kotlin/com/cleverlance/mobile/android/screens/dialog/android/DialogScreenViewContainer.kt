package com.cleverlance.mobile.android.screens.dialog.android

import android.app.Activity
import android.support.annotation.MainThread
import io.reactivex.disposables.Disposable

/** Creates dialogView view based on provided screen and shows it  */
class DialogScreenViewContainer(private val activity: Activity) : ScreenDispatcher<DialogScreen> {
    /**
     * Shows non-persistent dialog view
     * @return disposable that will hide the dialog view
     */
    @MainThread
    override fun show(screen: DialogScreen): Disposable {
        return screen.createDialogScreenView(activity)
                .apply { this.activity = this@DialogScreenViewContainer.activity }
                .show()
    }
}
