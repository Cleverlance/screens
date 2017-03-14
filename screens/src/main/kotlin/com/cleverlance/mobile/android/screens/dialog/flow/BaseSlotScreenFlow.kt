package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import com.cleverlance.mobile.android.screens.dialog.android.ScreenDispatcher
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.disposables.Disposable

abstract class BaseSlotScreenFlow<ScreenType> : ScreenFlow<ScreenType> {
    protected val NO_SCREEN = Any() as ScreenType
    /** Using null, when no presenter is bound.  */
    private val currentScreenSubject = BehaviorRelay.createDefault(NO_SCREEN)

    protected var currentScreen: ScreenType
        get() = currentScreenSubject.value
        set(screen) = currentScreenSubject.accept(screen)

    /** @return subscription can be used to unbind */
    fun subscribe(screenDispatcher: ScreenDispatcher<ScreenType>): Disposable {
        return currentScreenSubject
                .switchMap { screen ->
                    Completable.create {
                        if (NO_SCREEN != currentScreen) {
                            it.setDisposable(screenDispatcher.show(screen))
                        }
                    }.toObservable<Unit>()
                }
                .subscribe()
    }
}
