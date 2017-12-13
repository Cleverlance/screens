package com.cleverlance.mobile.android.screens.dialog.android

import io.reactivex.Observable
import io.reactivex.disposables.Disposable

/**
 * @param S screen type
 */
interface ScreenDispatcher<in S> {
    fun show(screen: S): Disposable
}

/** @return subscription can be used to unbind */
fun <S> Observable<S>.subscribe(screenDispatcher: ScreenDispatcher<S>): Disposable =
        switchMap { screen ->
            Observable.create<Unit> {
                it.setDisposable(screenDispatcher.show(screen))
            }
        }.subscribe()