package com.app.productdetail.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.app.productdetail.R
import com.app.productdetail.callback.OnProductSelectedCallback
import com.app.productdetail.model.ProductDetailsModel
import com.app.productdetail.utility.Util
import kotlinx.android.synthetic.main.activity_product_detail.view.imageViewProductImage
import kotlinx.android.synthetic.main.product_list_item.view.*

class ProductsHorizontalAdapter(val productSelectedCallback: OnProductSelectedCallback) :
    RecyclerView.Adapter<ProductsHorizontalAdapter.MyAdapter>() {

    private var data: MutableList<ProductDetailsModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter {
        return MyAdapter(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_list_item, parent, false),
            productSelectedCallback
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyAdapter, position: Int) = holder.bind(data[position])

    fun newDataInserted(data: MutableList<ProductDetailsModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getItemByPosition(position: Int): ProductDetailsModel {
        return data[position]
    }

    class MyAdapter(itemView: View, val productSelectedCallback: OnProductSelectedCallback) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: ProductDetailsModel) = with(itemView) {
            itemView.imageViewProductImage.setImageResource(item.shoeDrawable)

            val palette = context.getDrawable(item.shoeDrawable)?.toBitmap()?.let {
                Util.createPaletteSync(
                    it
                )
            }
            val color = palette?.lightMutedSwatch

            var gradientDrawable = GradientDrawable()
            gradientDrawable.colors = intArrayOf(color?.rgb ?:
            ContextCompat.getColor(context, R.color.colorWhite),
                color?.rgb ?:
                ContextCompat.getColor(context, R.color.colorWhite))
            gradientDrawable.cornerRadii = floatArrayOf(20f, 20f, 20f, 20f, 200f, 200f, 20f, 20f)

            itemView.constraintLayoutProductDetailsContainer.background = gradientDrawable
            itemView.textViewProductBrand.text = item.productName
            itemView.textViewProductName.text = item.productTitle

            setOnClickListener {
                productSelectedCallback.onProductSelected(adapterPosition, item,
                    itemView.textViewProductBrand, itemView.textViewProductName,
                    itemView.constraintLayoutProductDetailsContainer, itemView.imageViewProductImage)
            }
        }
    }
}