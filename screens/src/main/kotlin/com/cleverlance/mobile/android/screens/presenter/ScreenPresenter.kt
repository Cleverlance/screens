package com.cleverlance.mobile.android.screens.presenter

import android.app.Activity
import com.cleverlance.mobile.android.screens.domain.Screen
import com.cleverlance.mobile.android.screens.domain.ScreenInvoker
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenPresenter @Inject constructor() {
    private val screen: BehaviorRelay<Screen> = BehaviorRelay.create()

    fun back(activity: Activity): Boolean = getScreen().back(activity)

    fun screenObservable(): Observable<Screen> = screen

    @Deprecated(message = "probably nobody should need this - each screen knows that it shows itself")
            /* private */ fun getScreen(): Screen = screen.value

    fun setScreen(screen: Screen) = this.screen.accept(screen)

    fun ensureFirstScreen(invoker: ScreenInvoker) {
        if (!screen.hasValue()) invoker.createScreen()
    }

    fun onBackShowPrevious(): (Activity) -> Boolean {
        if (screen.hasValue()) {
            val previousScreen = getScreen()
            return { setScreen(previousScreen); true }
        } else {
            return { false }
        }
    }
}
