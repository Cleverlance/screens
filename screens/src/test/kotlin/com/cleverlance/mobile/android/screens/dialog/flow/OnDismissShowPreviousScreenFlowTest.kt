package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import org.junit.jupiter.api.Test

internal class OnDismissShowPreviousScreenFlowTest {
    @Test
    fun dismissShowsPreviousScreen() {
        val screenPresenter = ScreenPresenter()
        val flow = OnDismissShowPreviousScreenFlow(screenPresenter)

        val firstScreen = mock<BaseScreen>()

        screenPresenter.screen = firstScreen

        val secondScreen = mock<BaseScreen>()
        val dismissSecondScreen = flow.show(secondScreen)
        assertThat(screenPresenter.screen, equalTo(secondScreen))

        dismissSecondScreen.dispose()

        screenPresenter.screenObservable().test().assertValue(firstScreen)
        assertThat(screenPresenter.screen, equalTo(firstScreen))
    }
}