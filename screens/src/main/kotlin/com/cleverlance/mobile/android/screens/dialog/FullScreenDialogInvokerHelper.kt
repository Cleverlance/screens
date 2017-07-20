package com.cleverlance.mobile.android.screens.dialog

import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import io.reactivex.disposables.Disposable

abstract class FullScreenDialogInvokerHelper : GenericDialogInvokeHelper<BaseScreen>() {
    abstract var screenPresenter: ScreenPresenter
    override val screenFlow: ScreenFlow<BaseScreen>
        get() = object : ScreenFlow<BaseScreen> {
            override fun show(screen: BaseScreen, dialogResultCallback: DialogResultCallback<*>): Disposable {
                return screenPresenter.onDisposeShowCurrent().apply {
                    screen.onShow(this)
                    screenPresenter.setScreen(screen)
                }
            }
        }
}