package com.cleverlance.mobile.android.screens.domain

interface ScreenFactory {
    fun createScreen(): BaseScreen
}