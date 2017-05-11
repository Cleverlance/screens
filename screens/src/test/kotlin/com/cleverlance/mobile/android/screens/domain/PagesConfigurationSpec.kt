package com.cleverlance.mobile.android.screens.domain

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.natpryce.hamkrest.throws
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class PagesConfigurationSpec : SubjectSpek<PagerConfiguration>({
    subject { PagerConfiguration() }

    describe("no tabs") {
        context("get tab") {
            given("invalid position") {
                it("should throw exception") {
                    assertThat({ subject.getTab(0) }, throws<IndexOutOfBoundsException>())
                }
            }
        }

        context("count") {
            it("should get zero") {
                assertThat(subject.count(), equalTo(0))
            }
        }
    }

    context("register page") {
        it("should contain one page") {
            val page = mockPageScreen()

            subject.registerPage(page)

            assertThat(subject.count(), equalTo(1))
            assertThat(subject.getTab(0), equalTo(page))
        }

        it("should contain multiple pages") {
            val pages = listOf(mockPageScreen(), mockPageScreen())

            pages.forEach { subject.registerPage(it) }

            assertThat(subject.count(), equalTo(pages.size))
            pages.forEachIndexed { i, it -> assertThat(subject.getTab(i), equalTo(it)) }
        }

        it("should contain multiple pages via constructor") {
            val page1 = mockPageScreen()
            val page2 = mockPageScreen()
            val page3 = mockPageScreen()
            val pageConfig = PagerConfiguration(0, page1, page2, page3)

            assertThat(pageConfig.count(), equalTo(3))
        }
    }
})

private fun mockPageScreen() = mock<PageScreen>()