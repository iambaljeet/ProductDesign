package com.app.productdetail.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.app.productdetail.R
import com.app.productdetail.adapter.ProductColorAdapter
import com.app.productdetail.adapter.ProductSizeAdapter
import com.app.productdetail.callback.OnRecyclerViewItemClickCallback
import com.app.productdetail.model.ProductColorModel
import com.app.productdetail.model.ProductDetailsModel
import com.app.productdetail.model.ProductSizeModel
import com.app.productdetail.utility.Constants
import com.google.android.material.shape.ShapeAppearanceModel
import kotlinx.android.synthetic.main.activity_product_detail.*
import kotlinx.android.synthetic.main.toolbar_back.*

class ProductDetailActivity : AppCompatActivity(),
    OnRecyclerViewItemClickCallback {

    override fun onItemClick(view: View, position: Int) {
        when(view.id) {
            R.id.imageViewColor -> {
                productColorAdapter.setSelection(position)
            }
            R.id.cardViewSize -> {
                productSizeAdapter.setSelection(position)
            }
        }
    }

    var productDetailsModel: ProductDetailsModel? = null
    lateinit var productSizeAdapter: ProductSizeAdapter
    lateinit var productColorAdapter: ProductColorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        setSupportActionBar(toolbarMain)

        supportActionBar?.setHomeAsUpIndicator(getDrawable(R.drawable.ic_arrow_back_black_24dp))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)

        productDetailsModel =
            intent?.extras?.getParcelable(Constants.PRODUCT_DETAILS)

        setTransparentStatusBar()

        productSizeAdapter = ProductSizeAdapter(this)
        recyclerViewProductSizes.adapter = productSizeAdapter

        productColorAdapter = ProductColorAdapter(this)
        recyclerViewProductColors.adapter = productColorAdapter

        setProductSizes()
        setProductColors()

        productDetailsModel?.shoeDrawable?.let { imageViewProductImage.setImageResource(it) }
        textViewProductBrand.text = productDetailsModel?.productName
        textViewProductName.text = productDetailsModel?.productTitle

        viewBackground.shapeAppearanceModel = ShapeAppearanceModel.builder().setBottomLeftCornerSize(50f)
            .setBottomRightCornerSize(150f).build()
    }

    private fun setProductColors() {
        var productColorsList = mutableListOf<ProductColorModel>()

        productColorsList.add(
            ProductColorModel(
                ContextCompat.getColor(
                    this,
                    R.color.colorChoice1
                ), false
            )
        )
        productColorsList.add(
            ProductColorModel(
                ContextCompat.getColor(
                    this,
                    R.color.colorChoice2
                ), false
            )
        )
        productColorsList.add(
            ProductColorModel(
                ContextCompat.getColor(
                    this,
                    R.color.colorChoice3
                ), false
            )
        )

        productColorAdapter.newDataInserted(productColorsList)
    }

    private fun setProductSizes() {
        var productSizesList = mutableListOf<ProductSizeModel>()
        for (i in 1..5) {
            productSizesList.add(ProductSizeModel(i, "$i", false))
        }

        productSizeAdapter.newDataInserted(productSizesList)
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

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
