package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import android.view.ViewGroup
import io.reactivex.disposables.Disposables

internal class ScreenContainer(private val container: ViewGroup, private val activity: Activity) {
    private var presenterBindings = Disposables.disposed()

    internal fun setScreen(screen: Screen) {
        removeCurrentScreen()
        container.removeAllViews()

        screen.createView().run {
            activity = this@ScreenContainer.activity
            val view = createView(container)
            container.addView(view)
            presenterBindings = bindPresenter()
        }
    }

    internal fun removeCurrentScreen() {
        presenterBindings.dispose()

        //When in dispatchDetachedFromWindow() removeAllViews() would cause second dispatchDetachedFromWindow() invocation for child Views
        //Caused by: java.lang.NullPointerException: Attempt to invoke virtual method 'void android.support.v7.widget.GapWorker.remove(android.support.v7.widget.RecyclerView)' on a null object reference
        //at android.support.v7.widget.RecyclerView.onDetachedFromWindow(RecyclerView.java:2534)
    }
}