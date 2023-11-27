package com.example.ch_machinetest.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.ch_machinetest.R
import com.example.ch_machinetest.home.model.Product

class ProductsListingAdapter(
    context: Context, category: ArrayList<String>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context
    var category: ArrayList<String>

    init {
        this.context = context
        this.category = category
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.products_listing_item_lyt, parent, false)
        return MyViewHolder(v)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // var categoryName: Button

        init {

            // categoryName = itemView.findViewById(R.id.button) as Button
        }
    }

    override fun getItemCount(): Int {
        return 7
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

//        val cat = category?.get(position)
//        val categoryName = cat?.brand
//        holder.itemView.findViewById<Button>(R.id.button).setText(categoryName)

        val button = holder.itemView.findViewById<Button>(R.id.button)
        button.setText(category.get(position))

    }

}