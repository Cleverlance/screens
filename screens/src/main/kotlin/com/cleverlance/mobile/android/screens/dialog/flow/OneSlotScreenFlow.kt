package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import com.cleverlance.mobile.android.screens.dialog.android.ScreenDispatcher
import com.cleverlance.mobile.android.screens.dialog.android.subscribe
import com.cleverlance.mobile.android.screens.presenter.BaseScreenPresenter
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/**
 * One screen can be shown at a time. No stacking support.
 * @param ScreenType screen type
 */
class OneSlotScreenFlow<ScreenType>(private val noScreen: ScreenType) : ScreenFlow<ScreenType> {
    private val screenPresenter = object : BaseScreenPresenter<ScreenType>() {
        override val screenRelay = BehaviorRelay.createDefault<ScreenType>(noScreen)
    }
    var screen: ScreenType
        get() = screenPresenter.screen
        set(value) {
            screenPresenter.screen = screen
        }

    fun screenObservable(): Observable<ScreenType> = screenPresenter.screenRelay

    fun subscribe(dispatcher: ScreenDispatcher<ScreenType>) =
            screenObservable().subscribe(dispatcher)

    /** Called from presenter  */
    override fun show(screen: ScreenType): Disposable {
        val currentScreen = this.screen

        check(noScreen == currentScreen) { "Already showing $currentScreen" }

        // show dialog screen
        this.screen = screen

        return Disposables.fromAction { hide(screen) }
    }

    /** Called from presenter  */
    internal fun hide(screen: ScreenType) {
        val currentScreen = this.screen
        check(currentScreen === screen) { "Showing different screen than hiding $currentScreen" }

        // hide dialog screen
        this.screen = noScreen
    }
}
