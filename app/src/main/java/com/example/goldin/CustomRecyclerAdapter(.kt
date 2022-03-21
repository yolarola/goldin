package com.example.goldin

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.LayoutInflater

class CustomRecyclerAdapter(private val names: Array<Products>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var largeTextView: TextView? = null
        var smallTextView: TextView? = null

        init {
            largeTextView = itemView.findViewById(R.id.textViewLarge)
            smallTextView = itemView.findViewById(R.id.textViewSmall)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.largeTextView?.text = names[position].name
        holder.smallTextView?.text = "__________________________________________"

        holder.itemView.setOnClickListener{
            onItemClickListener.onItemClicked(names,position)
        }


    }

    override fun getItemCount(): Int {

        return names.size
    }



}
