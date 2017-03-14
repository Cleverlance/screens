package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.DialogResultCallback
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/**
 * One screen can be shown at a time. No stacking support.
 * @param ScreenType screen type
 */
class OneSlotScreenFlow<ScreenType> : BaseSlotScreenFlow<ScreenType>() {
    private var dialogResultCallback: DialogResultCallback<*>? = null

    /** Called from presenter  */
    override fun show(screen: ScreenType, dialogResultCallback: DialogResultCallback<*>): Disposable {
        val currentScreen = currentScreen

        check(NO_SCREEN == currentScreen) { "Already showing dialog screen $currentScreen" }

        this.dialogResultCallback = dialogResultCallback
        // show dialog screen
        this.currentScreen = screen

        return Disposables.fromAction { hide(screen) }
    }

    /** Called from presenter  */
    internal fun hide(screen: ScreenType) {
        val currentScreen = currentScreen
        check(currentScreen === screen) { "Showing different dialog screen $currentScreen" }

        // hide dialog screen
        this.currentScreen = NO_SCREEN
    }
}
