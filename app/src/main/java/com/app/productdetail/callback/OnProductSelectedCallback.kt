package com.app.productdetail.callback

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.app.productdetail.model.ProductDetailsModel

interface OnProductSelectedCallback {
    fun onProductSelected(
        adapterPosition: Int,
        item: ProductDetailsModel,
        textViewProductBrand: AppCompatTextView,
        textViewProductName: AppCompatTextView,
        constraintLayoutProductDetailsContainer: ConstraintLayout,
        imageViewProductImage: AppCompatImageView
    )
}