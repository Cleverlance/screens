package com.cleverlance.mobile.android.screens.presenter

import android.view.View
import android.view.ViewGroup
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.isA
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class BasePresenterViewSpec : SubjectSpek<BasePresenterView>({
    subject {
        object : BasePresenterView() {
            override fun createView(container: ViewGroup): View {
                throw UnsupportedOperationException()
            }

            override fun bindPresenter() = Disposables.empty()
        }
    }

    context("bind presenter") {
        it("should bind view to presenter via disposable") {
            assertThat(subject.bindPresenter(), isA<Disposable>())
        }
    }
})