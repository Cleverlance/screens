package com.cleverlance.mobile.android.screens.presenter

import com.cleverlance.mobile.android.screens.domain.BaseScreen
import com.cleverlance.mobile.android.screens.domain.ScreenFactory
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
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
            val screen = mock<BaseScreen>()

            subject.setScreen(screen)

            assertThat(subject.getScreen(), equalTo(screen))
        }
    }

    context("back") {
        it("should go back to previous screen") {
            val firstScreen = mock<BaseScreen>()

            subject.setScreen(firstScreen)

            subject.onDisposeShowCurrent().dispose()

            subject.screenObservable().test().assertValue(firstScreen)
            assertThat(subject.getScreen(), equalTo(firstScreen))
        }
    }

    context("subscribe to screen") {
        it("should get updated when screen is set") {

            val observer = subject.screenObservable().test()

            observer.assertNoValues()

            val screen = mock<BaseScreen>()

            subject.setScreen(screen)

            observer.assertValue(screen)
        }

        it("should have non-consume back action when going back from initial screen") {
            val screen = mock<BaseScreen> {
                whenever(it.onBackPressed()).thenReturn(false)
            }
            subject.setScreen(screen)
            assertThat(subject.back(mock()), equalTo(false))
        }
    }

    context("ensure first screen") {
        it("should create exactly one init screen") {
            val screen = mock<BaseScreen>()
            val screenFactory = mock<ScreenFactory> {
                whenever(it.createScreen()).thenReturn(screen)
            }

            subject.ensureFirstScreen(screenFactory)
            subject.ensureFirstScreen(screenFactory)

            verify(screenFactory, times(1)).createScreen()
            subject.screenObservable().test().assertValue(screen)
        }
    }
})