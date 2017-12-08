package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.DialogResultCallback
import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import com.cleverlance.mobile.android.screens.dialog.android.ScreenDispatcher
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Completable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

/**
 * One top screen can will be shown at a time.
 * @param ScreenType screen type
 */
open class StackScreenFlow<ScreenType> : ScreenFlow<ScreenType> {
    private val NO_SCREEN: ScreenType = Any() as ScreenType
    private val stack: MutableList<ScreenType> = mutableListOf(NO_SCREEN)

    private val updateSubject = BehaviorRelay.createDefault(Unit)

    /** @return subscription can be used to unbind */
    fun subscribe(screenDispatcher: ScreenDispatcher<ScreenType>): Disposable {
        return updateSubject
                .map { stack.last() }
                .switchMap { screen ->
                    Completable.create {
                        if (NO_SCREEN != screen) {
                            it.setDisposable(screenDispatcher.show(screen))
                        }
                    }.toObservable<Unit>()
                }
                .subscribe()
    }

    /** Called from presenter  */
    override fun show(screen: ScreenType, dialogResultCallback: DialogResultCallback<*>): Disposable {
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
