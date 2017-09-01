package com.cleverlance.mobile.android.screens.view

import android.view.View
import com.cleverlance.mobile.android.screens.domain.Screen
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class BasePagerAdapterSpec : SubjectSpek<ScreenPagerAdapter>({
    subject {
        object : ScreenPagerAdapter() {
            override fun getScreen(position: Int): Screen = mock()

            override fun getCount(): Int = 1
        }
    }

    context("is view from object") {
        given("valid objects") {
            it("should get true") {
                val view = mock<View>()
                val viewAsAny: Any = view

                assertThat(subject.isViewFromObject(view, viewAsAny), equalTo(true))
            }

            it("should get false") {
                assertThat(subject.isViewFromObject(mock(), Any()), equalTo(false))
            }
        }
    }
})