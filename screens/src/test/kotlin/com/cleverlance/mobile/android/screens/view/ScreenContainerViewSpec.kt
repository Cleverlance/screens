package com.cleverlance.mobile.android.screens.view

import android.app.Activity
import com.cleverlance.mobile.android.screens.domain.Screen
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.never
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.verify

@RunWith(JUnitPlatform::class)
internal class ScreenContainerViewSpec : SubjectSpek<ScreenContainerView>({
    given("activity context") {
        subject {
            ScreenContainerView(mock<Activity>(), null).apply { this.screenContainer = mock() }
        }

        context("attach to window") {
            it("should not set screen when screen is not present") {
                subject.attachToWindow()

                assertTrue(subject.isAttachedToAnyWindow)
                verify(subject.screenContainer, never()).setScreen(any())
            }
        }

        context("detach from window") {
            it("should remove screen") {
                subject.detachFromWindow()

                assertFalse(subject.isAttachedToAnyWindow)
                verify(subject.screenContainer).removeCurrentScreen()
            }
        }

        context("set screen") {
            val screen = mock<Screen>()

            it("should not set screen when screen is not attached") {
                subject.isAttachedToAnyWindow = false
                subject.setScreen(screen)

                verify(subject.screenContainer, never()).setScreen(screen)
            }

            it("should set screen when screen is attached") {
                subject.isAttachedToAnyWindow = true
                subject.setScreen(screen)

                verify(subject.screenContainer).setScreen(screen)
            }
        }
    }
})