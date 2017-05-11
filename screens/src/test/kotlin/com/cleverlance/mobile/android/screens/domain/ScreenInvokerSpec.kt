package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.*
import io.reactivex.disposables.Disposable
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class ScreenInvokerSpec : SubjectSpek<ScreenInvoker>({
    subject {
        object : ScreenInvoker() {
            override val screenPresenter: ScreenPresenter = spy()
            override val screenFactory: ScreenFactory = object : ScreenFactory {
                override fun createScreen(): BaseScreen {
                    return object : BaseScreen() {
                        override fun createView(): BasePresenterView {
                            return mock()
                        }
                    }
                }
            }
        }
    }

    it("should create screen") {
        subject.showScreen()

        verify(subject.screenPresenter).setScreen(any<BaseScreen>())
    }

    it("should create screen with reset") {
        val dispose = mock<Disposable>()
        subject.showScreen(dispose)

        argumentCaptor<BaseScreen>().run {
            verify(subject.screenPresenter).setScreen(capture())

            assertThat(allValues.size, equalTo(1))
            println(lastValue)
            assertThat(subject.screenPresenter.back(mock()), equalTo(true))
            verify(dispose).dispose()
        }
    }

    context("back") {
        it("should default back to previous screen") {
            val firstScreen = mock<BaseScreen>()

            subject.screenPresenter.setScreen(firstScreen)

            subject.showScreen()

            assertThat(subject.screenPresenter.back(mock()), equalTo(true)) // back consumed

            assertThat(subject.screenPresenter.getScreen(), equalTo(firstScreen))
        }
    }
})