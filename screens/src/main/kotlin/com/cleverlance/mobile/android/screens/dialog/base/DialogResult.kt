package com.cleverlance.mobile.android.screens.dialog.base

interface DialogResult

sealed class BaseDialogResult<D> : DialogResult {
    data class Success<D>(val data: D) : BaseDialogResult<D>()
    class Dismiss : BaseDialogResult<Any>()
}
