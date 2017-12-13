package com.cleverlance.mobile.android.screens.view

import android.app.Activity
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.domain.Screen
import com.cleverlance.mobile.android.screens.domain.createSelfBindingView

fun ViewGroup.setScreen(screen: Screen) {
    removeAllViews()
    addScreen(screen)
}

fun ViewGroup.addScreen(screen: Screen) {
    screen.createSelfBindingView(this, context as Activity)?.let { addView(it) }
}
