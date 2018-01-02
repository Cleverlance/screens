package com.cleverlance.mobile.android.screens.presenter

import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.domain.NoScreen
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class ScreenPresenterSpec : SubjectSpek<ScreenPresenter>({
    subject { ScreenPresenter() }

    it("should get NoScreen as default") {
        subject.screenObservable().test().assertValue { it is NoScreen }
    }

    context("set screen") {
        it("should put new screen on top") {
            val screen = mock<BaseScreen>()

            subject.screen = screen

            assertThat(subject.screen, equalTo(screen))
        }
    }

    context("subscribe to screen") {
        it("should get updated when screen is set") {

            val observer = subject.screenObservable().test()

            val screen = mock<BaseScreen>()

            subject.screen = screen

            assertThat(observer.values().last(), equalTo(screen))
        }

        it("should have non-consume back action when going back from initial screen") {
            val screen = mock<BaseScreen> {
                whenever(it.onBackPressed()).thenReturn(false)
            }
            subject.screen = screen
            assertThat(subject.back(mock()), equalTo(false))
        }
    }
})