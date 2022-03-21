package com.example.goldin

interface OnItemClickListener {

   // fun onItemClicled(position: Int){}

    fun onItemClicked(products: Array<Products>, position: Int)
}