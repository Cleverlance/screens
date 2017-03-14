package com.cleverlance.mobile.android.screens.dialog.flow

import com.cleverlance.mobile.android.screens.dialog.DialogResultCallback
import com.cleverlance.mobile.android.screens.dialog.android.DialogScreen
import com.cleverlance.mobile.android.screens.dialog.android.ScreenDispatcher
import com.nhaarman.mockito_kotlin.mock
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class OneSlotScreenFlowTest {
    lateinit var dialogResultCallback: DialogResultCallback<*>
    lateinit var dialogScreenFlow: OneSlotScreenFlow<DialogScreen>

    @Before
    fun setUp() {
        dialogResultCallback = mock()
        dialogScreenFlow = OneSlotScreenFlow<DialogScreen>()
    }

    @Test
    fun testDialogViewVisibilitySwitching() {
        val dialogScreenDispatcher1 = mock<ScreenDispatcher<DialogScreen>>()
        val subscription1 = dialogScreenFlow.subscribe(dialogScreenDispatcher1)
        verify<ScreenDispatcher<DialogScreen>>(dialogScreenDispatcher1).hide()

        val screen = mock(DialogScreen::class.java)
        dialogScreenFlow.show(screen, dialogResultCallback)

        verify<ScreenDispatcher<DialogScreen>>(dialogScreenDispatcher1).show(screen)

        // unbind one view and attach new one e.g. screen rotation
        subscription1.dispose()

        val dialogScreenDispatcher2 = mock<ScreenDispatcher<DialogScreen>>()

        val subscription2 = dialogScreenFlow.subscribe(dialogScreenDispatcher2)
        verify<ScreenDispatcher<DialogScreen>>(dialogScreenDispatcher2).show(screen)

        dialogScreenFlow.hide(screen)

        verify<ScreenDispatcher<DialogScreen>>(dialogScreenDispatcher2).hide()

        subscription2.dispose()
    }

}