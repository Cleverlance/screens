package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView

abstract class Screen {
    /** @return true if back action was consumed by the call */
    open val back: (Activity) -> Boolean = { false } // default does not consume back

    abstract fun presenterView(): BasePresenterView

    fun title() = presenterView().title()
}