package com.cleverlance.mobile.android.screens.view

import android.view.ViewManager
import org.jetbrains.anko.custom.ankoView

fun ViewManager.screenContainerView(theme: Int = 0) = screenContainerView(theme) {}

inline fun ViewManager.screenContainerView(theme: Int = 0, init: ScreenContainerView.() -> Unit) =
        ankoView({ ScreenContainerView(it, null) }, theme, init)