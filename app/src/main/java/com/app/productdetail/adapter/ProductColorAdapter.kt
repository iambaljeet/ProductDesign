package com.app.productdetail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.productdetail.R
import com.app.productdetail.callback.OnRecyclerViewItemClickCallback
import com.app.productdetail.model.ProductColorModel
import kotlinx.android.synthetic.main.product_colors_list_item.view.*

class ProductColorAdapter(val onRecyclerViewItemClickCallback: OnRecyclerViewItemClickCallback) : RecyclerView.Adapter<ProductColorAdapter.MyViewHolder>() {

    private var data: MutableList<ProductColorModel> = mutableListOf()
    private var lastSelectedItem = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.product_colors_list_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) = holder.bind(data[position], onRecyclerViewItemClickCallback)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int, payloads: MutableList<Any>) = holder.bind(data[position
    ], onRecyclerViewItemClickCallback)

    fun newDataInserted(data: MutableList<ProductColorModel>) {
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
        fun bind(item: ProductColorModel, onRecyclerViewItemClickCallback: OnRecyclerViewItemClickCallback) = with(itemView) {
            itemView.imageViewColor.setBackgroundColor(item.colorCode)
            if (item.isSelected) {
                itemView.imageViewSelector.visibility = View.VISIBLE
            } else {
                itemView.imageViewSelector.visibility = View.GONE
            }

            itemView.imageViewColor.setOnClickListener {view ->
                onRecyclerViewItemClickCallback.onItemClick(view, adapterPosition)
            }
        }
    }
}