package com.cleverlance.mobile.android.screens.view

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import com.cleverlance.mobile.android.screens.domain.PagerConfiguration
import com.cleverlance.mobile.android.screens.domain.Screen
import org.jetbrains.anko.*

open class BasePagerAdapter(val pagerConfig: PagerConfiguration) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup?, position: Int) =
            createScreenContainer(container!!, pagerConfig.getTab(position))
                    .apply { container.addView(this) }

    override fun destroyItem(container: ViewGroup, position: Int, view: Any) {
        container.removeView(view as View)
    }

    override fun isViewFromObject(view: View?, any: Any?) = view == any

    override fun getCount() = pagerConfig.count()

    private fun createScreenContainer(container: ViewGroup, screen: Screen) =
            object : AnkoComponent<ViewGroup> {
                override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
                    verticalLayout {
                        lparams(width = matchParent, height = wrapContent)

                        screenContainerView().lparams(width = matchParent, height = wrapContent)
                                .apply { setScreen(screen) }
                    }
                }
            }.createView(AnkoContext.create(container.context, container))
}