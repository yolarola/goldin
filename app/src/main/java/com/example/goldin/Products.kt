package com.example.goldin

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
@Serializable
class Products(
    var id: Int,
    var name: String?,
    var price: Int,
    var category: Int,
    var structure: String?,
    var weight: Int,
    var color: String?,
    var size: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun toString(): String {
        return "Cat [category: ${this.category}, name: ${this.name}, structure: ${this.structure}, price: ${this.price}, weight: ${this.weight}, color: ${this.color}, size: ${this.size}]"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(price)
        parcel.writeInt(category)
        parcel.writeString(structure)
        parcel.writeInt(weight)
        parcel.writeString(color)
        parcel.writeString(size)
    }



    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Products> {
        override fun createFromParcel(parcel: Parcel): Products {
            return Products(parcel)
        }

        override fun newArray(size: Int): Array<Products?> {
            return arrayOfNulls(size)
        }
    }
    fun setNull() {
//        var id : Int = 0
//        var name: String? = ""
//        var price: Int = 0
//        var category: Int = 0
//        var structure: String? = ""
//        var weight: Int = 0
//        var color: String? = ""
//        var size: String? = ""
        id = 0
        name = ""
        price = 0
        category = 0
        structure = ""
        weight= 0
        color = ""
        size = ""
    }

}