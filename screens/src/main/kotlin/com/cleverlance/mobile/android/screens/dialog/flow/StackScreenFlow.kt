package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/**
 * One top screen can will be shown at a time.
 * @param ScreenType screen type
 */
open class StackScreenFlow<ScreenType>(noScreen: ScreenType) : ScreenFlow<ScreenType> {
    private val stack: MutableList<ScreenType> = mutableListOf(noScreen)
    private val updateSubject = BehaviorRelay.createDefault(Unit)

    fun screenObservable(): Observable<ScreenType> = updateSubject
            .map { stack.last() }

    /** Called from presenter  */
    override fun show(screen: ScreenType): Disposable {
        stack.add(screen)
        updateSubject.accept(Unit)

        return Disposables.fromAction { hide(screen) }
    }

    /** Called from presenter  */
    internal fun hide(screen: ScreenType) {
        stack.remove(screen)
        updateSubject.accept(Unit)
    }
}
