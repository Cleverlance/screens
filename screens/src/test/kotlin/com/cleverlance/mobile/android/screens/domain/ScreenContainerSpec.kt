package com.cleverlance.mobile.android.screens.domain

import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class ScreenContainerSpec : SubjectSpek<ScreenContainer>({
    given("container") {
        val container = mock<ViewGroup>()

        subject { ScreenContainer(container, mock()) }

        context("remove current screen") {
            it("should empty container") {
                subject.removeCurrentScreen()

                verify(container).removeAllViews()
            }
        }

        context("set screen") {
            val screen = mock<Screen>()
            var presenterView: BasePresenterView? = null

            beforeEachTest {
                presenterView = mock<BasePresenterView> {
                    whenever(it.createView(any())).thenReturn(mock())
                }
                whenever(screen.createView()).thenReturn(presenterView)
            }

            it("should insert screen into container") {
                subject.setScreen(screen)

                verify(container).addView(any())
            }

            it("should initialize bindings") {
                subject.setScreen(screen)

                verify(presenterView!!).bindPresenter()
            }
        }
    }
})