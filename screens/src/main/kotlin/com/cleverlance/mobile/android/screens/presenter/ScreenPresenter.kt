package com.cleverlance.mobile.android.screens.presenter

import android.app.Activity
import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.domain.NoScreen
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable

class ScreenPresenter {
    private val screenRelay: BehaviorRelay<BaseScreen> = BehaviorRelay.createDefault(NoScreen())

    var screen: BaseScreen
        get() = screenRelay.value
        set(screen) = screenRelay.accept(screen)

    fun back(activity: Activity): Boolean = screen.onBackPressed()

    // TODO pass NoScreen or not?
    fun screenObservable(): Observable<BaseScreen> = screenRelay.filter { it !is NoScreen }
}
