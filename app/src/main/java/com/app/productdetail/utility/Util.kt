package com.app.productdetail.utility

import android.app.Activity
import android.content.res.Resources
import android.graphics.Bitmap
import androidx.palette.graphics.Palette


object Util {

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun getStatusBarHeight(activity: Activity): Int {
        var result = 0
        val resourceId = activity.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = activity.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

    // Generate palette synchronously and return it
    fun createPaletteSync(bitmap: Bitmap): Palette = Palette.from(bitmap).generate()

    // Generate palette asynchronously and use it on a different
    // thread using onGenerated()
    fun createPaletteAsync(bitmap: Bitmap) {
        Palette.from(bitmap).generate { palette ->
            // Use generated instance
        }
    }
}
