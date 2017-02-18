package com.cleverlance.mobile.android.screens.dialog

interface ScreenFactory<ResultType, ScreenType> {
    fun create(dialogResultCallback: DialogResultCallback<ResultType>): ScreenType
}
