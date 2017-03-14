package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import com.cleverlance.mobile.android.screens.dialog.android.ScreenDispatcher

import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

abstract class BaseSlotScreenFlow<ScreenType> : ScreenFlow<ScreenType> {
    protected val NO_SCREEN = Any() as ScreenType
    /** Using null, when no presenter is bound.  */
    private val currentScreenSubject = BehaviorSubject.createDefault(NO_SCREEN)

    protected var currentScreen: ScreenType
        get() = currentScreenSubject.value
        set(screen) = currentScreenSubject.onNext(screen)

    /** @return subscription can be used to unbind
     */
    fun subscribe(screenDispatcher: ScreenDispatcher<ScreenType>): Disposable {
        return currentScreenSubject
                .doOnDispose { screenDispatcher.hide() }
                .subscribe { currentScreen ->
                    if (NO_SCREEN != currentScreen) {
                        screenDispatcher.show(currentScreen)
                    } else {
                        screenDispatcher.hide()
                    }
                }
    }
}
