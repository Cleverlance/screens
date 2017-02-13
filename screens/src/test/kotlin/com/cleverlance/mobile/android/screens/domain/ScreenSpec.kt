package com.cleverlance.mobile.android.screens.domain

import android.app.Activity
import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class ScreenSpec : Spek({
    val back = mock<(Activity) -> Boolean> {
        whenever(it.invoke(any())).thenReturn(false)
    }
    val presenterView = mock<BasePresenterView>()
    val screen = object : Screen() {
        override val back = back
        override fun presenterView() = presenterView
    }

    context("previous") {
        it("should get valid previous screen") {
            assertThat(screen.back(mock()), equalTo(false))
        }
    }

    context("presenter view") {
        it("should get valid presenter view") {
            assertThat(screen.presenterView(), equalTo(presenterView))
        }
    }

    context("title") {
        it("should get valid title") {
            val title = "test"
            whenever(presenterView.title()).thenReturn(title)

            assertThat(screen.title(), equalTo(title))
        }
    }
})