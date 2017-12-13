package com.cleverlance.mobile.android.screens.presenter

import com.jakewharton.rxrelay2.BehaviorRelay

abstract class BaseScreenPresenter<ScreenType> {
    abstract val screenRelay: BehaviorRelay<ScreenType>

    var screen: ScreenType
        get() = screenRelay.value
        set(screen) = screenRelay.accept(screen)
}
