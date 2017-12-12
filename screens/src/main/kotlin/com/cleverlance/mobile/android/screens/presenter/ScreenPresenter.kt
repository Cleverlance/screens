package com.cleverlance.mobile.android.screens.presenter

import android.app.Activity
import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.domain.NoScreen
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

class ScreenPresenter {
    private val screen: BehaviorRelay<BaseScreen> = BehaviorRelay.createDefault(NoScreen())

    fun back(activity: Activity): Boolean = getScreen().onBackPressed()

    // TODO pass NoScreen or not?
    fun screenObservable(): Observable<BaseScreen> = screen.filter { it !is NoScreen }

    @Deprecated(message = "probably nobody should need this - each screen knows that it shows itself")
            /* private */ fun getScreen(): BaseScreen = screen.value

    fun setScreen(screen: BaseScreen) = this.screen.accept(screen)

    fun onDisposeShowCurrent(): Disposable {
        val previousScreen = getScreen() // capture current screen
        return Disposables.fromRunnable { setScreen(previousScreen) }
    }
}
