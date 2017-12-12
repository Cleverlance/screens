package com.cleverlance.mobile.android.screens.dialog

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.observers.TestObserver
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.mockito.*
import org.mockito.Mockito.*

class DialogInvokeHelperTest {
    private val anyResult = TestResult("AnyResult")
    private val anyError = Exception("UserCancelled")
    private lateinit var testResultSubscriber: TestObserver<TestResult>
    @Mock internal lateinit var screenFlow: ScreenFlow<AnyScreen>
    private lateinit var dialogInvokeHelper: GenericDialogInvokeHelper<AnyScreen>
    private lateinit var screenFactory: ScreenFactory<TestResult, AnyScreen>
    private lateinit var inOrder: InOrder
    @Mock private lateinit var onSuccess: Consumer<TestResult>
    @Mock private lateinit var onError: Consumer<Throwable>
    @Captor private lateinit var errorCaptor: ArgumentCaptor<Throwable>
    @Mock private lateinit var dismissScreen: Disposable
    // tests states
    private lateinit var dialogResultCallback: DialogResultCallback<TestResult>
    private lateinit var lastCreatedDialogScreen: AnyScreen

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testResultSubscriber = spy(TestObserver())
        inOrder = inOrder(screenFlow, testResultSubscriber, dismissScreen)
        dialogInvokeHelper = object : GenericDialogInvokeHelper<AnyScreen>() {
            override val screenFlow: ScreenFlow<AnyScreen> get() = this@DialogInvokeHelperTest.screenFlow
        }
        screenFactory = createScreenFactory()
        whenever(screenFlow.show(any())).thenReturn(dismissScreen)
    }

    @Test
    fun testShouldReturnResultOnConfirm() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(testResultSubscriber)
        dismissWithResult(anyResult)

        testResultSubscriber.assertValue(anyResult)
    }

    internal fun createScreenFactory(): ScreenFactory<TestResult, AnyScreen> {
        return testScreenFactory
    }

    internal fun dismissWithResult(result: TestResult) {
        dialogResultCallback.dismissWithResult(result)
    }

    internal fun dismissWithError(error: Throwable) {
        dialogResultCallback.dismissWithError(error)
    }

    @Test
    fun testShouldReturnError() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(testResultSubscriber)
        dismissWithError(anyError)

        testResultSubscriber.assertError(anyError)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testShouldThrowExceptionNoResultRequestedButDelivered() {
        // callback not registered
        dismissWithResult(anyResult)
    }

    @Test(expected = UninitializedPropertyAccessException::class)
    fun testShouldThrowExceptionNoResultRequestedButDeliveredError() {
        // callback not registered
        dismissWithError(anyError)
    }

    @Test
    fun testShouldDeliverFirstResultWhenMultipleResultsDelivered() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(onSuccess)

        dismissWithResult(anyResult)
        dismissWithResult(anyResult)

        verify<Consumer<TestResult>>(onSuccess, times(1)).accept(anyResult)
    }

    @Test
    fun testShouldDeliverFirstErrorWhenMultipleErrorsDelivered() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(
                Consumer<TestResult> { fail("success result not expected") },
                onError
        )

        dismissWithError(TestExpectedException())
        dismissWithError(anyError)

        verify<Consumer<Throwable>>(onError, times(1)).accept(errorCaptor.capture())
        assertThat(errorCaptor.value, instanceOf(TestExpectedException::class.java))
    }

    @Test
    fun testShouldDeliverFirstResultWhenResultAndErrorDelivered() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(onSuccess, onError)

        dismissWithResult(anyResult)
        dismissWithError(anyError)

        verify<Consumer<TestResult>>(onSuccess, times(1)).accept(anyResult)
        verifyZeroInteractions(onError)
    }

    @Test
    fun testShouldDeliverFirstErrorWhenErrorAndResultDelivered() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(
                Consumer<TestResult> { _ -> fail("success result not expected") },
                onError
        )

        dismissWithError(TestExpectedException())
        dismissWithResult(anyResult)

        verify<Consumer<Throwable>>(onError, times(1)).accept(errorCaptor.capture())
        assertThat(errorCaptor.value, instanceOf(TestExpectedException::class.java))
    }

    @Test
    fun testDialogShouldNotShowUntilSubscribedSuccess() {
        dialogInvokeHelper.forResult(screenFactory) // do not subscribe

        verify<ScreenFlow<AnyScreen>>(screenFlow, never()).show(any())
    }

    @Test
    fun testDialogViewVisibilitySwitchingOnSuccess() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(testResultSubscriber)

        verify<ScreenFlow<AnyScreen>>(screenFlow).show(lastCreatedDialogScreen)

        dismissWithResult(anyResult)

        inOrder.verify(dismissScreen).dispose()
        inOrder.verify(testResultSubscriber).onNext(anyResult)
        inOrder.verify(testResultSubscriber).onComplete()

        testResultSubscriber.assertValue(anyResult)
        testResultSubscriber.assertComplete()
    }

    @Test
    fun testDialogViewVisibilitySwitchingOnError() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(testResultSubscriber)

        verify<ScreenFlow<AnyScreen>>(screenFlow).show(lastCreatedDialogScreen)

        dismissWithError(anyError)

        inOrder.verify(dismissScreen).dispose()
        inOrder.verify(testResultSubscriber).onError(anyError)

        testResultSubscriber.assertError(anyError)
    }

    @Test
    fun testUnsubscribeHidesDialogScreen() {
        val subscription = dialogInvokeHelper.forResult(screenFactory).subscribeWith(testResultSubscriber)

        verify<ScreenFlow<AnyScreen>>(screenFlow).show(lastCreatedDialogScreen)

        subscription.dispose()

        inOrder.verify(dismissScreen).dispose()
    }

    @Test
    fun testShowDialogAgainInCallbackWithSuccess() {
        showDialogRecursivelyWithSuccess()

        dismissWithResult(anyResult)
    }

    internal fun showDialogRecursivelyWithSuccess() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(
                { _ -> showDialogRecursivelyWithSuccess() },
                { error -> throw RuntimeException("Error not expected", error) } // do nothing
        )
    }

    @Test
    fun testShowDialogAgainInCallbackWithError() {
        showDialogRecursivelyWithError()

        dismissWithError(TestExpectedException())
    }

    internal fun showDialogRecursivelyWithError() {
        dialogInvokeHelper.forResult(screenFactory).subscribe(
                { _ -> throw IllegalStateException("Ok not expected") }, // do nothing             ,
                { error ->
                    assertThat(error, CoreMatchers.instanceOf(TestExpectedException::class.java))
                    showDialogRecursivelyWithError()
                }
        )
    }

    @Test
    fun testForResultShouldSupportSubscribingMultipleTimes() {
        val commonForResult = dialogInvokeHelper.forResult(screenFactory)

        commonForResult.subscribe(testResultSubscriber)
        dismissWithError(anyError)

        // cannot reuse subscriber after it is unsubscribed
        testResultSubscriber = TestObserver.create<TestResult>()
        commonForResult.subscribe(testResultSubscriber)
        dismissWithError(anyError)
    }

    @Test
    fun testParallelSubscribesAreAllowed() {
        val commonForResult = dialogInvokeHelper.forResult(screenFactory)

        commonForResult.subscribe()
        commonForResult.subscribe()
    }

    internal class AnyScreen

    internal val testScreenFactory: ScreenFactory<TestResult, AnyScreen> = { dialogResultCallback ->
        this@DialogInvokeHelperTest.dialogResultCallback = dialogResultCallback
        val anyScreen = mock(AnyScreen::class.java)
        lastCreatedDialogScreen = anyScreen
        anyScreen
    }

    internal data class TestResult(private val label: String)

    internal class TestExpectedException : Exception()
}

