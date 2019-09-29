package com.app.productdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.productdetail.R
import com.app.productdetail.callback.OnRecyclerViewItemClickCallback
import com.app.productdetail.model.ProductSizeModel
import kotlinx.android.synthetic.main.product_sizes_list_item.view.*

class ProductSizeAdapter(val onRecyclerViewItemClickCallback: OnRecyclerViewItemClickCallback) : RecyclerView.Adapter<ProductSizeAdapter.MyViewHolder>() {

    private var data: MutableList<ProductSizeModel> = mutableListOf()
    private var lastSelectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_sizes_list_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(data[position], onRecyclerViewItemClickCallback)

    fun newDataInserted(data: MutableList<ProductSizeModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun setSelection(position: Int) {
        if (lastSelectedItem >= 0) {
            data[lastSelectedItem].isSelected = false
            notifyItemChanged(lastSelectedItem, true)
        }
        lastSelectedItem = position
        data[lastSelectedItem].isSelected = true
        notifyItemChanged(lastSelectedItem, true)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductSizeModel, onRecyclerViewItemClickCallback: OnRecyclerViewItemClickCallback) = with(itemView) {
            itemView.textViewProductSize.text = item.sizeText

            if (item.isSelected) {
                itemView.cardViewSize.setCardBackgroundColor(resources.getColor(R.color.colorSizeSelected))
                itemView.textViewProductSize.setTextColor(resources.getColor(R.color.colorSizeSelectedText))
            } else {
                itemView.cardViewSize.setCardBackgroundColor(resources.getColor(R.color.colorSizeUnSelected))
                itemView.textViewProductSize.setTextColor(resources.getColor(R.color.colorSizeUnSelectedText))
            }

            itemView.cardViewSize.setOnClickListener {view ->
                onRecyclerViewItemClickCallback.onItemClick(view, adapterPosition)
            }
        }
    }
}