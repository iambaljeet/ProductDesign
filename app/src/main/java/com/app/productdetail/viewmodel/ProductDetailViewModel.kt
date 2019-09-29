package com.app.productdetail.viewmodel

import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel

class ProductDetailViewModel : ViewModel() {

    @BindingAdapter("productId")
    fun loadImage(view: AppCompatButton, productId: Int) {
        Log.e("ProductDetailViewModel", "productId: $productId")
    }

    @BindingAdapter("productId")
    fun onAddToCartClicked(view: AppCompatButton, productId: Int) {
        Log.e("ProductDetailViewModel", "productId: $productId")
    }
}