package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.disposables.Disposables
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertFails

@RunWith(JUnitPlatform::class)
internal class ScreenSpec : Spek({
    val presenterView = mock<BasePresenterView>()
    val screen = object : BaseScreen() {
        override fun createView() = presenterView
    }

    context("onBackPressed()") {
        it("should crash before onShow()") {
            assertFails {
                screen.onBackPressed()
            }
        }

        it("should dispose screen disposable") {
            val dispose = Disposables.empty()

            screen.onShow(dispose)

            assertThat(screen.onBackPressed(), equalTo(true))
            assert(dispose.isDisposed)
        }
    }

    context("presenter view") {
        it("should get valid presenter view") {
            assertThat(screen.createView(), equalTo(presenterView))
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