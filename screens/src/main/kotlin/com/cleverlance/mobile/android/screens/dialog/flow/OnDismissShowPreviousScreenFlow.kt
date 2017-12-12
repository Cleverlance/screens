package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.ScreenFlow
import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import io.reactivex.disposables.Disposable

class OnDismissShowPreviousScreenFlow(private val screenPresenter: ScreenPresenter) : ScreenFlow<BaseScreen> {
    override fun show(screen: BaseScreen): Disposable =
            screenPresenter.onDisposeShowCurrent().apply {
                screen.onShow(this)
                screenPresenter.setScreen(screen)
            }
}