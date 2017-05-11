package com.cleverlance.mobile.android.screens.presenter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.isA
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.disposables.Disposables
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
internal class BaseInflaterScreenViewSpec : SubjectSpek<BaseInflaterScreenView>({
    subject {
        object : BaseInflaterScreenView() {
            override var viewLayoutResource = 0
            override fun bindPresenter() = Disposables.empty()
        }
    }

    context("create view") {
        it("should inflate view") {
            val inflater = mock<LayoutInflater> {
                whenever(it.inflate(any<Int>(), any(), any())).thenReturn(mock())
            }
            val context = mock<Context> {
                whenever(it.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater)
            }
            val container = mock<ViewGroup> {
                whenever(it.context).thenReturn(context)
            }
            assertThat(subject.createView(container), isA<View>())
        }
    }
})