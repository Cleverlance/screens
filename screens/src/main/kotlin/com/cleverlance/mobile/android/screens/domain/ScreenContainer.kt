package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import android.view.ViewGroup
import io.reactivex.disposables.Disposables

internal class ScreenContainer(private val container: ViewGroup, private val activity: Activity) {
    private var presenterBindings = Disposables.disposed()

    internal fun setScreen(screen: Screen) {
        removeCurrentScreen()

        screen.presenterView().run {
            activity = this@ScreenContainer.activity
            val view = createView(container)
            container.addView(view)
            presenterBindings = bindPresenter()
        }
    }

    internal fun removeCurrentScreen() {
        presenterBindings.dispose()

        container.removeAllViews()
    }
}