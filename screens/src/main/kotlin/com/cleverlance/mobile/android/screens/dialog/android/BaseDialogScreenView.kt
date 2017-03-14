package com.cleverlance.mobile.android.screens.dialog.android

import android.app.Dialog

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/** Use if dialog has special logic that needs presenter.  */
abstract class BaseDialogScreenView protected constructor() : DialogScreenView {
    abstract fun createDialog(): Dialog

    abstract fun bindPresenter(): Disposable

    override fun show(): Disposable {
        val dialog = createDialog()
        dialog.show()
        val presenterBindings = bindPresenter()

        return CompositeDisposable(presenterBindings, Disposables.fromRunnable { dialog.dismiss() })
    }
}
