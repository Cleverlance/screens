package com.cleverlance.mobile.android.screens.dialog

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

/**
 * Dialog screen will be shown on subscribe and dismissed eagerly before onSuccess, onError or on unsubscribe
 * @param S screen type class
 */
class GenericDialogInvokeHelper<S>(private val screenFlow: ScreenFlow<S>) {

    fun completable(screenFactory: ScreenFactory<Unit, S>): Completable {
        return Completable.fromSingle<Unit>(forResult(screenFactory))
    }

    /** @param R result type*/
    fun <R> forResult(screenFactory: ScreenFactory<R, S>): Single<R> {
        // Timber.d("forResult %s ", this)
        return Single.using<R, CompositeDisposable>({ CompositeDisposable() }, { dismissScreen ->
            Single.create<R> { source ->
                // Timber.d("forResult onSubscribe %s", this)

                val dialogResultCallback = object : DialogResultCallback<R> {
                    /** Dismisses the dialog and returns the value to forResult()  */
                    override fun dismissWithResult(result: R) {
                        // Timber.d("dismissWithResult %s %s", this, result)
                        source.onSuccess(result)
                    }

                    /** Dismisses the dialog and returns the error to forResult()  */
                    override fun dismissWithError(error: Throwable) {
                        // Timber.d("dismissWithError %s %s", this, error)
                        source.onError(error)
                    }
                }

                val screen = screenFactory.create(dialogResultCallback)
                // show dialog screen
                dismissScreen.add(screenFlow.show(screen, dialogResultCallback))
            }
        }, CompositeDisposable::dispose) // dismiss dialog
    }
}
