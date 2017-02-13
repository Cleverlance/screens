package com.cleverlance.mobile.android.screens.domain

import com.cleverlance.mobile.android.screens.presenter.BasePresenterView
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.isA
import com.nhaarman.mockito_kotlin.mock
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith


@RunWith(JUnitPlatform::class)
internal class PageInvokerSpec : Spek({
    it("page invoker should create page") {
        val pageInvoker = object : PageInvoker<BasePresenterView>() {}
        val viewProvider = mock<ViewProvider<BasePresenterView>>()
        pageInvoker.viewProvider = viewProvider
        assertThat(pageInvoker.createPage(), isA<PageScreen<BasePresenterView>>())
    }
})