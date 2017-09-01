package com.cleverlance.mobile.android.screens.dialog

import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import io.reactivex.disposables.Disposable

abstract class OnDisposeShowPreviousSceenFlow(val screenPresenter: ScreenPresenter) : ScreenFlow<BaseScreen> {
    override fun show(screen: BaseScreen, dialogResultCallback: DialogResultCallback<*>): Disposable =
            screenPresenter.onDisposeShowCurrent().apply {
                screen.onShow(this)
                screenPresenter.setScreen(screen)
            }
}