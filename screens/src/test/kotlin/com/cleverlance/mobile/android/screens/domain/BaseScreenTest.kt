package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class BaseScreenSpec : Spek({
    context("create view") {
        it("should create view") {
            val createdView = mock<BasePresenterView>()
            val screen = object : BaseScreen() {
                override fun createView(): BasePresenterView {
                    return createdView
                }
            }

            assertThat(screen.createView(), equalTo(createdView))
        }
    }
})