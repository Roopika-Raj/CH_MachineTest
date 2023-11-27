package com.example.ch_machinetest.home.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ch_machinetest.R
import com.example.ch_machinetest.home.model.Product
import com.example.ch_machinetest.productdetails.view.ProductDetailsFragment

class ImageListingAdapter(
    context: Context
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context
    var productDetails: ArrayList<Product>? = null

    init {
        this.context = context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_listing_adapter_lyt, parent, false)
        return MyViewHolder(v)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var title: TextView
        var price: TextView
        var discountPercentage: TextView

        init {

            title = itemView.findViewById(R.id.brandnName) as TextView
            price = itemView.findViewById(R.id.mrp) as TextView
            discountPercentage = itemView.findViewById(R.id.discountView) as TextView

        }
    }

    override fun getItemCount(): Int {
        return productDetails?.size ?: 0
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val products = productDetails?.get(position)
        val name = products?.title
        val mrp = products?.price
        val discountText = products?.discountPercentage

        holder.itemView.findViewById<TextView>(R.id.brandnName).text = name
        holder.itemView.findViewById<TextView>(R.id.mrp).text = mrp.toString()
        holder.itemView.findViewById<TextView>(R.id.discountView).text = discountText.toString()

        val id = products?.id
        val productImage = holder.itemView.findViewById<ImageView>(R.id.images)
        val images = products?.images
        if (images != null) {
            Glide.with(context).load(images.get(0)).into(productImage)

            holder.itemView.setOnClickListener(object : View.OnClickListener {
                override fun onClick(p0: View?) {
                    if (id != null) {
                        val productsDeatils = ProductDetailsFragment()
                        productsDeatils.selectedProductId = id
                        if (id != null) {
                            val manager = (context as AppCompatActivity).supportFragmentManager
                            manager.beginTransaction()
                                .add(R.id.homeContainer, productsDeatils)
                                .addToBackStack(null)
                                .commit()
                        }
                    }
                }

            })
        }

    }

    fun setData(products: ArrayList<Product>) {
        productDetails = products
    }
}


