package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/**
 * One screen can be shown at a time. No stacking support.
 * @param ScreenType screen type
 */
abstract class OneSlotScreenFlow<ScreenType>(private val noScreen: ScreenType) : ScreenFlow<ScreenType> {
    abstract var screen: ScreenType

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
