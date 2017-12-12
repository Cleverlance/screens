package com.cleverlance.mobile.android.screens.view

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.domain.Screen
import com.cleverlance.mobile.android.screens.domain.createSelfBindingView

abstract class ScreenPagerAdapter : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int) =
            createScreenContainer(container, getScreen(position))
                    .apply { container.addView(this) }

    abstract fun getScreen(position: Int): Screen

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View, any: Any) = view == any

    private fun createScreenContainer(container: ViewGroup, screen: Screen) =
            screen.createSelfBindingView(container, container.context as Activity)
}