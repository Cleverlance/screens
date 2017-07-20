package com.cleverlance.mobile.android.screens.view

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.domain.Screen
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.matchParent
import org.jetbrains.anko.verticalLayout

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
            object : AnkoComponent<ViewGroup> {
                override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
                    verticalLayout {
                        lparams(width = matchParent, height = matchParent)

                        screenContainerView().lparams(width = matchParent, height = matchParent)
                                .apply { setScreen(screen) }
                    }
                }
            }.createView(AnkoContext.create(container.context, container))
}