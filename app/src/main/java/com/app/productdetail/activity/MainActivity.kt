package com.app.productdetail.activity

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearSnapHelper
import com.app.productdetail.R
import com.app.productdetail.adapter.ProductsHorizontalAdapter
import com.app.productdetail.callback.OnProductSelectedCallback
import com.app.productdetail.callback.OnSelectedItemChangeListener
import com.app.productdetail.model.ProductDetailsModel
import com.app.productdetail.utility.Constants
import com.app.productdetail.utility.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnProductSelectedCallback,
    OnSelectedItemChangeListener {

    override fun onSelectedItemChanged(id: Int) {
    }

    override fun onProductSelected(
        adapterPosition: Int,
        item: ProductDetailsModel,
        textViewProductBrand: AppCompatTextView,
        textViewProductName: AppCompatTextView,
        constraintLayoutProductDetailsContainer: ConstraintLayout,
        imageViewProductImage: AppCompatImageView
    ) {
        var sharedElementsPair4 = Pair<View, String>(imageViewProductImage, ViewCompat.getTransitionName(imageViewProductImage))

        var pairs = ArrayList<Pair<View, String>>()
        pairs.add(sharedElementsPair4)

        var pairsList: Array<Pair<View, String>> = pairs.toTypedArray()

        val productDetailsModel = productsHorizontalAdapter.getItemByPosition(adapterPosition)

        val intent = Intent(this, ProductDetailActivity::class.java)
        intent.putExtra(Constants.PRODUCT_DETAILS, productDetailsModel)

        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this, *pairsList
        )

        startActivity(intent, options.toBundle())
    }

    private var data: MutableList<ProductDetailsModel> = mutableListOf()
    lateinit var productsHorizontalAdapter: ProductsHorizontalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setTransparentStatusBar()

        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerViewProducts)

        val statusBarHeight = Util.getStatusBarHeight(this)

        val layoutParamsRecyclerViewProducts: ConstraintLayout.LayoutParams = recyclerViewProducts.layoutParams as ConstraintLayout.LayoutParams
        layoutParamsRecyclerViewProducts.setMargins(15, statusBarHeight, 15, 15)
        recyclerViewProducts.layoutParams = layoutParamsRecyclerViewProducts

        productsHorizontalAdapter = ProductsHorizontalAdapter(this)
        recyclerViewProducts.adapter = productsHorizontalAdapter

        bottomNavigationHome.setDefaultItem(2)
        bottomNavigationHome.setOnSelectedItemChangeListener(this)

        setData()
    }

    private fun setTransparentStatusBar() {
        this.window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
            statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setData() {
        data.add(ProductDetailsModel(1, "Nike", "Series 1", R.drawable.shoe_1))
        data.add(ProductDetailsModel(2, "Nike", "Series 2", R.drawable.shoe_2))
        data.add(ProductDetailsModel(3, "Nike", "Series 3", R.drawable.shoe_3))
        data.add(ProductDetailsModel(4, "Nike", "Series 4", R.drawable.shoe_4))
        data.add(ProductDetailsModel(5, "Nike", "Series 5", R.drawable.shoe_5))
        data.add(ProductDetailsModel(6, "Nike", "Series 6", R.drawable.shoe_6))
        data.add(ProductDetailsModel(7, "Nike", "Series 7", R.drawable.shoe_7))
        data.add(ProductDetailsModel(8, "Nike", "Series 8", R.drawable.shoe_8))

        productsHorizontalAdapter.newDataInserted(data)
    }
}
