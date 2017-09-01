package com.cleverlance.mobile.android.screens.domain

import android.view.View
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.nhaarman.mockito_kotlin.*
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
            it("should not empty container") {
                subject.removeCurrentScreen()

                verify(container, never()).removeAllViews()
            }
        }

        context("set screen") {
            val screen = mock<Screen>()
            val presenterView = mock<BasePresenterView>()
            val createdView = mock<View>()

            beforeEachTest {
                reset(container, presenterView)
                whenever(screen.createView()).thenReturn(presenterView)
                whenever(presenterView.createView(any())).thenReturn(createdView)
            }

            it("should empty container") {
                subject.setScreen(screen)

                verify(container).removeAllViews()
            }

            it("should insert screen view into container") {
                subject.setScreen(screen)

                verify(container).addView(createdView)
            }

            it("should initialize bindings") {
                subject.setScreen(screen)

                verify(presenterView).bindPresenter()
            }
        }
    }
})