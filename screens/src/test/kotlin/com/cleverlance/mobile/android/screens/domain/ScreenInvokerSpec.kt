package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import com.cleverlance.mobile.android.screens.presenter.ScreenPresenter
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.*
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class ScreenInvokerSpec : SubjectSpek<ScreenInvoker>({
    subject {
        object : ScreenInvoker() {
            override val screenPresenter: ScreenPresenter = spy()
            override val screenFactory: ScreenFactory = object : ScreenFactory {
                override fun createScreen(back: ((Activity) -> Boolean)): Screen {
                    return BaseScreen(back = back,
                            viewProvider = mock <ViewProvider <*>>())
                }
            }
        }
    }

    it("should create screen") {
        subject.createScreen()

        verify(subject.screenPresenter).setScreen(any<BaseScreen<*>>())
    }

    it("should create screen with reset") {
        subject.createScreen({ false })

        argumentCaptor<Screen>().run {
            verify(subject.screenPresenter).setScreen(capture())

            assertThat(allValues.size, equalTo(1))
            println(lastValue)
            assertThat(subject.screenPresenter.back(mock()), equalTo(false))
        }
    }

    context("back") {
        it("should default back to previous screen") {
            val firstScreen = mock<Screen>()

            subject.screenPresenter.setScreen(firstScreen)

            subject.createScreen()

            assertThat(subject.screenPresenter.back(mock()), equalTo(true)) // back consumed

            assertThat(subject.screenPresenter.getScreen(), equalTo(firstScreen))
        }
    }
})