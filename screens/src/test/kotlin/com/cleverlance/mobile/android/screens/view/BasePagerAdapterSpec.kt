package com.cleverlance.mobile.android.screens.view

import android.view.View
import com.cleverlance.mobile.android.screens.domain.PagerConfiguration
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.SubjectSpek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class BasePagerAdapterSpec : SubjectSpek<BasePagerAdapter>({
    subject {
        val pageConfig = mock<PagerConfiguration>()
        object : BasePagerAdapter(pageConfig) {}
    }

    context("is view from object") {
        given("nulls") {
            it("should get true") {
                assertThat(subject.isViewFromObject(null, null), equalTo(true))
            }
        }

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