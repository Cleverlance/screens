package com.cleverlance.mobile.android.screens.presenter

import com.cleverlance.mobile.android.screens.domain.Screen
import com.cleverlance.mobile.android.screens.domain.ScreenInvoker
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class ScreenPresenterSpec : SubjectSpek<ScreenPresenter>({
    subject { ScreenPresenter() }

    context("get top screen") {
        it("should get no screen by default") {
            subject.screenObservable().test().assertNoValues()
        }
    }

    context("set screen") {
        it("should put new screen on top") {
            val screen = mock<Screen>()

            subject.setScreen(screen)

            assertThat(subject.getScreen(), equalTo(screen))
        }
    }

    context("back") {
        it("should go back to previous screen") {
            val firstScreen = mock<Screen>()

            subject.setScreen(firstScreen)

            subject.onBackShowPrevious()(mock())

            subject.screenObservable().test().assertValue(firstScreen)
            assertThat(subject.getScreen(), equalTo(firstScreen))
        }
    }

    context("subscribe to screen") {
        it("should get updated when screen is set") {

            val observer = subject.screenObservable().test()

            observer.assertNoValues()

            val screen = mock<Screen>()

            subject.setScreen(screen)

            observer.assertValue(screen)
        }

        it("should have non-consume back action when going back from initial screen") {
            val screen = mock<Screen> {
                whenever(it.back).thenReturn { false }
            }
            subject.setScreen(screen)
            assertThat(subject.back(mock()), equalTo(false))
        }
    }

    context("ensure first screen") {
        it("should create exactly one init screen") {
            val screenInvoker = mock<ScreenInvoker<BasePresenterView>> {
                whenever(it.createScreen()).then { subject.setScreen(mock()) }
            }

            subject.ensureFirstScreen(screenInvoker)
            subject.ensureFirstScreen(screenInvoker)

            verify(screenInvoker, times(1)).createScreen()
        }
    }
})