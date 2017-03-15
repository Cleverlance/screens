package com.cleverlance.mobile.android.screens.dialog

interface ScreenFactory<ResultType, out ScreenType> {
    fun create(dialogResultCallback: DialogResultCallback<ResultType>): ScreenType
}
