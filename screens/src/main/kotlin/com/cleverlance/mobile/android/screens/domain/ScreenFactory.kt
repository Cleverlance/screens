package com.cleverlance.mobile.android.screens.domain

import android.app.Activity

interface ScreenFactory {
    fun createScreen(back: ((Activity) -> Boolean)): Screen
}