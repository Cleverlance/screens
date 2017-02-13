package com.cleverlance.mobile.android.screens.view

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.cleverlance.mobile.android.screens.domain.Screen
import com.cleverlance.mobile.android.screens.domain.ScreenContainer

class ScreenContainerView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    internal var screenContainer = ScreenContainer(this, context as Activity)
    internal var isAttachedToAnyWindow = false
    private var screen: Screen? = null

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        attachToWindow()
    }

    override fun onDetachedFromWindow() {
        detachFromWindow()

        super.onDetachedFromWindow()
    }

    internal fun attachToWindow() {
        isAttachedToAnyWindow = true
        screen?.run { screenContainer.setScreen(this) }
    }

    internal fun detachFromWindow() {
        screenContainer.removeCurrentScreen()
        isAttachedToAnyWindow = false
    }

    fun setScreen(screen: Screen) {
        this.screen = screen
        if (isAttachedToAnyWindow) screenContainer.setScreen(screen)
    }
}
