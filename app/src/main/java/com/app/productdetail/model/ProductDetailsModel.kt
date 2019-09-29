package com.app.productdetail.model

import android.os.Parcel
import android.os.Parcelable

class ProductDetailsModel(var productId: Int, var productName: String?, var productTitle: String?, var shoeDrawable: Int): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(productId)
        parcel.writeString(productName)
        parcel.writeString(productTitle)
        parcel.writeInt(shoeDrawable)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ProductDetailsModel> {
        override fun createFromParcel(parcel: Parcel): ProductDetailsModel {
            return ProductDetailsModel(parcel)
        }

        override fun newArray(size: Int): Array<ProductDetailsModel?> {
            return arrayOfNulls(size)
        }
    }
}