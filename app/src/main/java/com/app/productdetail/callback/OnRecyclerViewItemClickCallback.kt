package com.app.productdetail.callback

import android.view.View

interface OnRecyclerViewItemClickCallback {
    fun onItemClick(view: View, position: Int)
}