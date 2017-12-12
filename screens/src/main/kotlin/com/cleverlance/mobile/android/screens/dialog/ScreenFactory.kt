package com.cleverlance.mobile.android.screens.dialog

typealias ScreenFactory<ResultType, ScreenType> = (DialogResultCallback<ResultType>) -> ScreenType
