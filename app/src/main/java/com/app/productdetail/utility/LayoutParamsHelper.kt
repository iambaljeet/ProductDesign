package com.app.productdetail.utility

import android.view.Gravity
import android.widget.FrameLayout

/**
 * @author S.Shahini
 * @since 11/13/16
 * this class used for providing necessary layout params
 */

object LayoutParamsHelper {

    fun getTabItemIconLayoutParams(): FrameLayout.LayoutParams? {
        val iconSize =
            Util.dpToPx(Dimens.TAB_ICON_SIZE)
        val layoutParams = FrameLayout.LayoutParams(iconSize, iconSize)
        layoutParams.gravity = Gravity.CENTER
        return layoutParams
    }
}
